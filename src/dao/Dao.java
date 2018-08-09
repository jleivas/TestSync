/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.InternMail;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.abstractclasses.SyncStringId;
import entities.User;
import entities.abstractclasses.SyncIntId;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.Log;
import fn.OptionPane;
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
        if(object instanceof SyncStringId){
            ((SyncStringId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncStringId)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
        }
        if(object instanceof SyncIntId){
            ((SyncIntId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncIntId)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
        }
        if(GV.isOnline()){
            if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
                ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
            if(GV.REMOTE_SYNC.exist(object)){
                if(object instanceof SyncIntId){
                    OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el nombre.", 2);
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
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet.", 2);
            }
        }
        return false;
    }

    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        if(object instanceof SyncStringId){
            ((SyncStringId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncStringId)object).setLastHour(Cmp.hourToInt(new Date()));
        }
        if(object instanceof SyncIntId){
            ((SyncIntId)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncIntId)object).setLastHour(Cmp.hourToInt(new Date()));
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
                            temp.setStock(temp.getStock()-LocalInventario.getStock(idLente));
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
            }else{
                OptionPane.showMsg("No se pudo reducir el stock", "El lente no se encuentra disponible.", 2);
            }
            return false;
        }catch(IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException ex){
            return false;
        }
    }
    
    public boolean delete(String cod,int id, Object type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Log.setLog(className,Log.getReg());
        Object temp =  null;
        if(GV.isOnline()){
            temp =  GV.REMOTE_SYNC.getElement(cod,id, type);
            if(temp != null){//valida si ya existe el desname
                if(temp instanceof SyncStringId){
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
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
                    ((SyncStringId)temp).setEstado(0);
                    ((SyncStringId)temp).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                    ((SyncStringId)temp).setLastHour(Cmp.hourToInt(new Date()));
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
        if(GV.isOnline()){
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
        if(GV.isCurrentDate(GV.LAST_UPDATE)){
            return;//solo hace una actualizacion por día.
        }
        try {
            if(GV.isOnline()){
                ArrayList<Object> lista1= GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type);
                int size1 = lista1.size();
                ArrayList<Object> lista2= GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type);
                int size2 = lista2.size();
                
                if(type instanceof Ficha){
                    sincronizeFicha();
                }
                for (Object object : lista1) {
                    GV.calcularPorcentaje(size1);
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                }
                GV.resetPorcentaje();
                for (Object object : lista2) {
                    GV.calcularPorcentaje(size2);
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                }
            }else{
                for (Object object : GV.LOCAL_SYNC.listar("-2",new User())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Convenio())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Convenio)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cliente())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Cristal())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Descuento())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Doctor())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
                }
                for (Object object : GV.LOCAL_SYNC.listar("-2",new Oficina())) {//falta opcion en listar
                    sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
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
     * 
     * @param strParam id de Ficha (Válido sólo para Armazónes).
     * @param intParam tipo de armazon [0: Cerca, 1: Lejos] (Válido sólo para Armazónes).
     * @param type tipo de clase a consultar
     * @return 
     */
    public String getCurrentCod(String strParam, int intParam, Object type){
        Log.setLog(className,Log.getReg());
        return GV.LOCAL_SYNC_FICHA.getId(strParam, intParam, type);
    }

    public void createFicha(Ficha ficha, HistorialPago hp) {
        try {
            add(ficha.getCliente());
//
//                //Guardar valores en BD
//                
//                //guardar historial de pago
//                if(!load.guardarHitorialPago(hp) && abono > 0){
//                    OptionPane.showMsg(null, "No se pudo completar la operación [1108]", "Error al guardar Ficha",JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//                //descontar de inventario lejos y cerca
//
//                
//                if(load.guardarFicha(ficha,GV.ID_USER)){
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error", "No se pudo insertar algunos valores.\n\n"+ex.getMessage(), 3);
        }
    }
}
