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

    public void sincronize(Object type) {
        Log.setLog(className,Log.getReg());
        try {
            if(GV.isOnline()){
                /*Cliente*/
                if(type instanceof Cliente){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cliente())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cliente)object);
                    }
                }
                /*Convenio*/
                if(type instanceof Convenio){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Convenio())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Convenio)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Convenio())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Convenio)object);
                    }
                }
                /*Cristal*/
                if(type instanceof Cristal){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Cristal())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Cristal)object);
                    }
                }
                /*Descuento*/
                if(type instanceof Descuento){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Descuento())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Descuento)object);
                    }
                }
                /*Doctor*/
                if(type instanceof Doctor){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Doctor())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Doctor)object);
                    }
                }
                /*Institucion*/
                if(type instanceof Institucion){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Institucion)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Institucion)object);
                    }
                }
                /*InternMail*/
                if(type instanceof InternMail){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (InternMail)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (InternMail)object);
                    }
                }
                /*Institucion*/
                if(type instanceof Inventario){
                    for (Object object : GV.REMOTE_SYNC_FICHA.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC_FICHA, GV.REMOTE_SYNC_FICHA, (Inventario)object);
                    }
                    for (Object object : GV.LOCAL_SYNC_FICHA.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC_FICHA, GV.REMOTE_SYNC_FICHA, (Inventario)object);
                    }
                }
                /*Lente*/
                if(type instanceof Lente){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Lente)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Lente)object);
                    }
                }
                /*Oficina*/
                if(type instanceof Oficina){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new Oficina())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Oficina)object);
                    } 
                }
                /*RegistroBaja*/
                if(type instanceof RegistroBaja){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (RegistroBaja)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (RegistroBaja)object);
                    }
                }
                /*TipoPago*/
                if(type instanceof TipoPago){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (TipoPago)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (TipoPago)object);
                    }
                }
                /*Usuario*/
                if(type instanceof User){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,new User())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,new User())) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (User)object);
                    }
                }
                /*  ENTIDADES RELACIONADAS CON LAS FICHAS   */
                /*Armazon*/
                if(type instanceof Armazon){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Armazon)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Armazon)object);
                    }
                }
                /*Despacho*/
                if(type instanceof Despacho){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Despacho)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (Despacho)object);
                    }
                }
                /*HistorialPago*/
                if(type instanceof HistorialPago){
                    for (Object object : GV.REMOTE_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (HistorialPago)object);
                    }
                    for (Object object : GV.LOCAL_SYNC.listar(GV.LAST_UPDATE,type)) {
                        sync.Sync.add(GV.LOCAL_SYNC, GV.REMOTE_SYNC, (HistorialPago)object);
                    }
                }
                if(type instanceof Ficha){
                    sincronizeFicha();
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

    private void sincronizeFicha() throws SQLException, ClassNotFoundException {
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
}
