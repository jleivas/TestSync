/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this objectlate file, choose Tools | Templates
 * and open the objectlate in the editor.
 */
package dao;

import entities.Cristal;
import entities.Equipo;
import entities.InternMail;
import entities.Inventario;
import entities.Lente;
import entities.User;
import entities.abstractclasses.SyncStringId;
import entities.abstractclasses.SyncClass;
import entities.abstractclasses.SyncFichaClass;
import entities.abstractclasses.SyncIntId;
import entities.abstractclasses.SyncIntIdValidaName;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.SubProcess;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesVariables;
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
    
    public boolean addOnInit(Object object) throws InstantiationException, IllegalAccessException {
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
        if(object instanceof SyncIntId)//se pueden agregar solo si tienen conexion a internet
            ((SyncIntId)object).setId(GV.LOCAL_SYNC.getMaxId(object));

        if(GV.LOCAL_SYNC.exist(object)){
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
        
        return false;
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
                if(object instanceof User){
                    if(((User)object).getId() == 1 || ((User)object).getId() == 2){
                        ((SyncIntId)object).setId(((SyncIntId)object).getId());
                    }else{
                        ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
                    }
                }else{
                    ((SyncIntId)object).setId(GV.REMOTE_SYNC.getMaxId(object));
                }
                
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
                    if(object instanceof InternMail){
                        return (sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object) &&
                                sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object));
                    }else{
                        return sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
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
    
    public boolean updateFromUI(Object object){
        if(validaEntity(object)){
            if(GV.licenciaLocal()){
                OptionPane.showMsg("NO CREATED", "function ist created!", 3);
                return false;
            }else{
                return updateRemote(object);
            }
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
                    /**
                     * Se inserta un registro temporal con las cantidades a reducir
                     */
                    if(LocalInventario.insert(idLente,cantidad)){
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
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe o fué modificado\n"
                        + "sincronice los datos para solucionar este error.", 2);
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
        if(type instanceof Lente){
            Inventario inv = (Inventario)get(GV.inventarioName(), 0, new Inventario());
            if(inv != null){
                GV.setInventarioSeleccionado(inv.getId());
            }
            Object lente =  LocalInventario.getLente(cod);
            GV.setInventarioSeleccionado(0);
            return lente;
        }
        return GV.LOCAL_SYNC.getElement(cod,id,type);
    }

    public static void sincronize(Object type) {
        Log.setLog(className,Log.getReg());
        boolean esLente = (type instanceof Lente);
        if(GV.isCurrentDate(GV.LAST_UPDATE)){//validar plan de licencia
            return;//solo hace una actualizacion por día.
        }
        try {
            if(GV.isOnline()){
                if(type instanceof Ficha){
                    type = new EtiquetFicha();
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
                        System.out.println(""+object.getClass().getName());
                        if(esLente){
                            System.out.println("es lente");
                        }
                        GV.porcentajeSubCalcular(size1+size2);
                        sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                }
                if(esLente){
                    /**
                     * actualizar stock
                     */
                    //se obtiene una lista recien descargada procesada con los stocks actualizados
                    lista2 = LocalInventario.listarLentesForSync();
                    int tam1 = lista2.size();
                    for (Object object : lista2) {
                        GV.porcentajeSubCalcular(tam1);
                        sync.Sync.addRemoteSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                        sync.Sync.addLocalSync(GV.LOCAL_SYNC, GV.REMOTE_SYNC, object);
                    }
                    LocalInventario.deleteAllRegistry("-2");
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


    /**
     * Retorna el id actual de las entidades Armazon, Despacho, Ficha, HistorialPago y RegistroBaja
     * @param type tipo de clase a consultar
     * @return 
     */
    public String getCurrentCod(Object type){
        Log.setLog(className,Log.getReg());
        if(type instanceof SyncFichaClass){
            return GV.LOCAL_SYNC.getMaxId(type)+"-"+GV.LOCAL_SYNC.getIdEquipo();
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
            if(ficha.getCerca() != null){
                if(!GV.getFicha().getCerca().getMarca().isEmpty()){
                    decreaseStock(ficha.getCerca().getMarca(), 1);
                }
            }
            if(ficha.getLejos() != null){
                if(!GV.getFicha().getLejos().getMarca().isEmpty()){
                    decreaseStock(ficha.getLejos().getMarca(), 1);
                }
            }
            if(hp != null){
                add(hp);
            }
            SubProcess.activateConnectionOnline();
            GV.sendMailFicha(ficha);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean usernameYaExiste(String username) {
        if(GV.licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoFree() ||
           GV.licenciaTipoPlan() == GlobalValuesVariables.licenciaTipoLocal()){
            return (GV.LOCAL_SYNC.getElement(username, 0, new User())!=null);
        }else{
            return (GV.REMOTE_SYNC.getElement(username, 0, new User())!=null);
        }
    }

    
    public boolean addFromUI(Object object){
        if(validaEntity(object)){
            if(GV.licenciaLocal()){
                addLocal(object);
            }else{
                addRemote(object);
            }
            return true;
        }
        return false;
    }
    
    private void addLocal(Object object) {
        if(object instanceof SyncIntId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            if(object instanceof SyncIntIdValidaName){
                addSyncIntIdValidaNameLocal(object);
            }else{
                addSyncIntIdLocal(object);
            }
        }
        if(object instanceof SyncStringId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            addSyncStringIdLocal(object);
        }
        OptionPane.showMsg("No se puede insertar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void addRemote(Object object) {
        if(object instanceof SyncIntId){
            if(!GV.isOnline()){
                OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet.", 2);
                return;
            }
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            ((SyncIntId)object).setId(GV.LOCAL_SYNC.getMaxId(object));
            if(((SyncIntId)object).getId() < 0){
                OptionPane.showMsg("Error de conexión", "No se pudo obtener conexión desde la base de datos remota.", 2);
                return;
            }
            if(object instanceof SyncIntIdValidaName){
                addSyncIntIdValidaNameRemote(object);
                return;
            }else{
                addSyncIntIdRemote(object);
                return;
            }
        }
        if(object instanceof SyncStringId){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));//solo se actualizan lastuodates para crear objetos
            addSyncStringIdRemote(object);
            return;
        }
        OptionPane.showMsg("No se puede insertar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
    }

    private void addSyncIntIdValidaNameLocal(Object object) {
        SyncIntIdValidaName entity = (SyncIntIdValidaName) GV.LOCAL_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
        if(entity == null){
            if(GV.LOCAL_SYNC.add(object)){
                msgEntityAdded();
            }
            msgEntityNotAdded();
        }else{
            msgInvalidName(entity.getEstado());
        }
    }

    private void addSyncIntIdLocal(Object object) {
        GV.LOCAL_SYNC.add(object);
    }
    
    private void addSyncIntIdRemote(Object object) {
        if(GV.REMOTE_SYNC.add(object)){
            GV.LOCAL_SYNC.add(object);
            msgEntityAdded();
        }
        msgEntityNotAdded();
    }
    
    private void msgInvalidName(int status){
        if(status == 0){
            OptionPane.showMsg("Imposible registrar elemento", "Ya existe un registro con el nombre ingresado pero se encuentra elimidado,\n"
                + "debes cambiar el nombre para poder continuar o restaurar el elemento eliminado.", 2);
        }else{
            OptionPane.showMsg("Imposible registrar elemento", "Ya existe un registro con el nombre ingresado,\n"
                + "debes cambiar el nombre para poder continuar.", 2);
        }
    }
    
    private void msgEntityAdded(){
        OptionPane.showMsg("Proceso finalizado", "El registro a sido guardado exitosamene.", 1);
    }
    
    private void msgEntityNotAdded(){
        OptionPane.showMsg("Proceso finalizado", "El registro no se ha podido guardar\n"
                + "ha ocurrido un error inesperado durante la operación.", 2);
    }

    private void addSyncStringIdLocal(Object object) {
        SyncStringId entity = (SyncStringId)GV.LOCAL_SYNC.getElement(((SyncStringId)object).getCod(), 0, object);
        if(entity == null){
            GV.LOCAL_SYNC.add(object);
        }else{
            if(entity.getEstado() == 0){
                if(OptionPane.getConfirmation("El registro ya existe", 
                        "Los datos ingresados corresponden a un registro anulado.\n"
                        + "Los datos se actualizarán y el registro se restaurará si confirmas los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                }
            }else{
                if(OptionPane.getConfirmation("El registro ya existe", "No se ha podido guardar el registro a menos que \n"
                        + "confirmes los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                }
            }  
        }
    }
    
    private void addSyncStringIdRemote(Object object) {
        SyncStringId entity = (SyncStringId)GV.LOCAL_SYNC.getElement(((SyncStringId)object).getCod(), 0, object);
        if(entity == null){
            GV.LOCAL_SYNC.add(object);
            msgEntityAdded();
        }else{
            if(entity.getEstado() == 0){
                if(OptionPane.getConfirmation("El registro ya existe", 
                        "Los datos ingresados corresponden a un registro anulado.\n"
                        + "Los datos se actualizarán y el registro se restaurará si confirmas los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityAdded();
                }
            }else{
                if(OptionPane.getConfirmation("El registro ya existe", "No se ha podido guardar el registro a menos que \n"
                        + "confirmes los cambios.\n"
                        + "¿Deseas actualizar los datos?", 2)){
                    GV.LOCAL_SYNC.update(object);
                    msgEntityAdded();
                }
            }  
        }
    }

    private void addSyncIntIdValidaNameRemote(Object object) {
        SyncIntIdValidaName entity = (SyncIntIdValidaName) GV.LOCAL_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
        if(entity == null){
            if(GV.REMOTE_SYNC.add(object)){
                GV.LOCAL_SYNC.add(object);
                msgEntityAdded();
            }else{
                msgEntityNotAdded();
            }
        }else{
            msgInvalidName(entity.getEstado());
        }
    }

    private boolean validaEntity(Object object) {
        if(object == null){
            return false;
        }
        if(object instanceof Cristal){
            Cristal obj = (Cristal)object;
            if(obj.getStr(obj.getNombre()).length() <= 3){
                OptionPane.showMsg("Nombre incorrecto", "El registro debe tener un nombre válido.\n"
                        + "Información a considerar:\n"
                        + "- El campo nombre no debe estar vacío.\n"
                        + "- El nombre debe tener más de tres caracteres.", 2);
                return false;
            }
            if(obj.getPrecio() <= 0){
                OptionPane.showMsg("Precio incorrecto", "No se puede registrar un lente con precio " + GV.strToPrice(obj.getPrecio()) + ".", 2);
                return false;
            }
            return true;
        }
        OptionPane.showMsg("Entidad no validada", "No se ha cumplido con las validaciones en esta entidad.", 3);
        return false;
    }

    private boolean updateRemote(Object object) {
        if(object instanceof SyncClass){
            ((SyncClass)object).setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
            ((SyncClass)object).setLastHour(Cmp.hourToInt(new Date()));
            if(object instanceof SyncIntId){
                if(!GV.isOnline()){
                    OptionPane.showMsg("No se puede modificar el registro", "Para poder modificar estos datos debes tener acceso a internet.", 2);
                    return false;
                }
                if(object instanceof SyncIntIdValidaName){
                    SyncIntIdValidaName entity = (SyncIntIdValidaName)GV.REMOTE_SYNC.getElement(((SyncIntIdValidaName)object).getNombre(), 0, object);
                    if(entity == null){
                        return GV.LOCAL_SYNC.update(object);
                    }else{
                        if(entity.getId() == ((SyncIntIdValidaName)object).getId()){
                            return GV.LOCAL_SYNC.update(object);
                        }else{
                            msgInvalidName(entity.getEstado());
                            return false;
                        }
                    }
                }else{
                    return GV.LOCAL_SYNC.update(object);
                }
            }
            if(object instanceof SyncStringId){
                return false;
            }
        }
        OptionPane.showMsg("No se puede modificar registro", "La entidad enviada no tiene un formato válido\n"
                + "\n"
                + "Detalle: " + object.getClass().getName(), 2);
        return false;
    }
}
