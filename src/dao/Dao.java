/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.InternMail;
import entities.Lente;
import entities.abstractclasses.SyncStringId;
import entities.abstractclasses.SyncClass;
import entities.abstractclasses.SyncFichaClass;
import entities.abstractclasses.SyncIntId;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.SubProcess;
import fn.date.Cmp;
import fn.mail.Send;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sync.entities.LocalInventario;

/**
 *
 * @author sdx
 */
public class Dao{
    private static String className="Dao";

    private Send mailSend = new Send();
    /**
     * Sólo crea datos, si ya existen no los puede agregar.
     * Útil solo para registros independientes de la base de datos remota
     * @param object
     * @return 
     */
    public boolean sendMessage (InternMail msg) throws InstantiationException, IllegalAccessException{
        Log.setLog(className,Log.getReg());
        msg.setEstado(1);
        String mail = msg.getDestinatario().getEmail();
        if(mail != null || !mail.isEmpty()){
            mailSend.sendMessageMail(msg.getAsunto(), mail);
        }
        return add(msg);
    }
   /**
    * Agrega registros a la base de datos, si ya existen los actualiza, útil para sincronización de bases de datos.
    * 
    * Comprueba si existe antes de agregar, en User comprueba si ya existe un username igual,
    * en cristal, descuento y oficina compara si ya existe un nombre igual (en estos casos no busca por id).
    * @param object
    * @return
    * @throws InstantiationException
    * @throws IllegalAccessException 
    */
    public boolean add(Object object) throws InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        if(object == null){
            return false;//ultima modificacion sin verificar en todos los casos de uso
        }
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
        }
        if(object instanceof SyncFichaClass){
            if(!(object instanceof Ficha)){
                ((SyncFichaClass) object).setCod(getCurrentCod(object));
            }
        }
        if(GV.isOnline()){
            if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
                ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
            if(GV.REMOTE_SYNC.exist(object)){
                if(object instanceof SyncIntId){
                    if(!GV.isCurrentDate(((SyncIntId)object).getLastUpdate())){
                        OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                            + "Para poder ingresar un nuevo registro debes cambiar el nombre.", 2);
                    }
                }else{
                    return update(object);
                }
            }else{
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            if(object instanceof SyncStringId){
                if(!GV.LOCAL_SYNC.exist(object)){
                    return update(object);
                }else{
                    try {
                        return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet."+object.toString(), 2);
            }
        }
        return false;
    }

    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));
        }
        try {
            return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean decreaseStock(String idLente, int cantidad)  {
        try{
            Lente temp = (Lente) get(idLente, 0, new Lente());
            int newStock = 0;
            if(temp != null){
                newStock = temp.getStock() - cantidad;
                if(newStock >= 0){
                    if(LocalInventario.insert(idLente,cantidad)){
                        if(GV.isOnline()){
                            sincronize(new Lente());
                            temp = (Lente)GV.REMOTE_SYNC.getElement(idLente, 0, new Lente());
                            temp.setStock(temp.getStock()-LocalInventario.stockDescontado(idLente));
                            if(temp.getStock() < 0){
                                temp.setStock(0);
                            }
                            if(LocalInventario.deleteAllRegistry(idLente)){
                                update(temp);
                            }else{
                                return false;
                            }
                        }
                        return true;
                    }
                }else{
                    OptionPane.showMsg("No se pudo reducir el stock", "La cantidad a reducir es mayor que el stock disponible", 2);
                }
            }
            return false;
        }catch(IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException ex){
            return false;
        }
    }
    
    public boolean delete(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GV.isOnline() && !(type instanceof Ficha)){
            temp =  GV.REMOTE_SYNC.getElement(cod,id, type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(temp instanceof Ficha){
                        ((SyncStringId)temp).setEstado(((((SyncStringId)temp).getEstado())*-1));
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(0);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }  
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }else{
            temp =  GV.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(temp instanceof Ficha){
                        ((SyncStringId)temp).setEstado(((((SyncStringId)temp).getEstado())*-1));
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(0);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }  
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(0);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }
        return false;
    }

    public boolean restore(String cod,int id,Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GV.isOnline() && !(type instanceof Ficha)){
            temp =  GV.REMOTE_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(1);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(1);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }else{
            temp =  GV.LOCAL_SYNC.getElement(cod,id,type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    if(type instanceof Ficha){
                        ((SyncStringId)temp).setEstado((((SyncStringId)temp).getEstado())*-1);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }else{
                        ((SyncStringId)temp).setEstado(1);
                        ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                        ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
                    }
                } 
                if(temp instanceof SyncIntId){
                    ((SyncIntId)temp).setEstado(1);
                    ((SyncIntId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncIntId)temp).setLastHour(Cmp.hourToInt(new Date()));
                }
                try {
                    return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", 2);
            }
        }
        return false;
    }
    
    /**
     * Retorna un elemento de la base de datos local
     * @param cod
     * @param id
     * @param type
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public Object get(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        return GV.LOCAL_SYNC.getElement(cod,id,type);
    }

    public static void sincronize(Object type) {
        Log.setLog(className,Log.getReg());
        if(GV.isCurrentDate(GV.LAST_UPDATE)){//validar plan de licencia
            return;//solo hace una actualizacion por día.
        }
        try {
            if(GV.isOnline()){
                if(type instanceof Ficha){
                    sincronizeFicha();
                    return;
                }
                ArrayList<Object> lista1= GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type);
                int size1 = lista1.size();
                ArrayList<Object> lista2= GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type);
                int size2 = lista2.size();
                if(size1 > 0){
                    for (Object object : lista1) {
                        GV.porcentajeSubCalcular(size1+size2);
                        sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                }
                 if(size2 > 0){
                     for (Object object : lista2) {
                         GV.porcentajeSubCalcular(size1+size2);
                        sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                 }
            } 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Object> listar(String param, Object type){
        if(type instanceof Lente){
            try {
                return LocalInventario.listarLentes(param);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                return new ArrayList<>();
            }
        }
        return GV.LOCAL_SYNC.listar(param, type);
    }
    
    /**
     * 
     * @param remitente 0 si no se necesita
     * @param destinatario 0 si no se necesita
     * @param estado 0: todos, 1: no leidos, 2 leidos
     * @return 
     */
    public ArrayList<InternMail> mensajes(int remitente, int destinatario, int estado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        return GV.LOCAL_SYNC.mensajes(remitente, destinatario, estado);
    }
//    public void sincronize() {
//        Log.setLog(className,Log.getReg());
//        System.out.println(Log.getLog());
//        try {
//            if(GV.isOnline()){
//                /*Usuario*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new User())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                /*Cliente*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                /*Cristal*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                /*Descuento*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                /*Doctor*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                /*Oficina*/
//                for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                } 
//            }else{
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new User())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cliente())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cristal())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Descuento())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Doctor())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
//                }
//                for (Object object : GV.LOCAL_SYNC.listar("-2",new Oficina())) {//falta opcion en listar
//                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
//                }
//            }  
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private static void sincronizeFicha() throws SQLException, ClassNotFoundException {
        sincronize(new EtiquetFicha());
        sincronize(new Armazon());
        sincronize(new Despacho());
        sincronize(new HistorialPago());
        for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Ficha())) {
            sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Ficha)object);
        }
        for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Ficha())) {
            sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Ficha)object);
        }
    }

    /**
     * Retorna el id actual de las entidades Armazon, Despacho, Ficha, HistorialPago y RegistroBaja
     * @param type tipo de clase a consultar
     * @return 
     */
    public String getCurrentCod(Object type){
        Log.setLog(className,Log.getReg());
        if(type instanceof SyncFichaClass){
            return GV.LOCAL_SYNC.getMaxId(type)+"/"+GV.LOCAL_SYNC.getIdEquipo();
        }else{
            OptionPane.showMsg("Instancia de datos errónea", "El tipo de datos ingresado no es válido para obtener el identificador.", 3);
            return null;
        }
        
    }

    public void createFicha(Ficha ficha, HistorialPago hp) {
        try {
            SubProcess.suspendConnectionOnline();
            add(ficha.getCliente());
            add(ficha.getCerca());
            add(ficha.getLejos());
            add(ficha.getDespacho());
            add(ficha.getDoctor());
            add(ficha);
            if(ficha.getCerca() != null || !GV.getFicha().getCerca().getMarca().isEmpty()){
                decreaseStock(ficha.getCerca().getMarca(), 1);
            }
            if(ficha.getLejos() != null || !GV.getFicha().getLejos().getMarca().isEmpty()){
                decreaseStock(ficha.getLejos().getMarca(), 1);
            }
            if(hp != null){
                add(hp);
            }
            SubProcess.activateConnectionOnline();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
