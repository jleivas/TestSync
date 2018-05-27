/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import fn.GlobalValues;
import fn.Log;
import fn.OptionPane;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class UserDao implements Dao{
    private static String className = "UserDao";
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean add(Object object){
        Log.setLog(className,Log.getReg());
        User user =  (User)object;
        user.setLastUpdate(new Date());
        if(GlobalValues.isOnline()){
            try {
                user.setId(GlobalValues.REMOTE_SYNC.getMaxUserId());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!GlobalValues.REMOTE_SYNC.userExist(user.getUsername()))
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            else{
                OptionPane.showMsg("No se puede crear nuevo usuario", "El Username ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el username.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede crear nuevo usuario", "Para poder ingresar un nuevo registro debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     *
     * @param object
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean update(Object object){
        Log.setLog(className,Log.getReg());
        User user =  (User)object;
        user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(GlobalValues.isOnline()){
            User old = (User)GlobalValues.REMOTE_SYNC.getUser(user.getUsername());
            if(old == null){//no existe ningun registro con el mismo username
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(old.getId() == user.getId()){//inserta sin validar username
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(!GlobalValues.REMOTE_SYNC.userExist(user.getUsername()))//valida si ya existe el nuevo username
                    try {
                        return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                else{
                    OptionPane.showMsg("No se puede actualizar usuario", "El Username ya se encuentra utilizado,\n"
                            + "Para poder ingresar un nuevo registro debes cambiar el username.", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else{
            OptionPane.showMsg("No se puede actualizar el usuario", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean delete(String id){
        Log.setLog(className,Log.getReg());
        User user =  null;
        if(GlobalValues.isOnline()){
            user =  (User)GlobalValues.REMOTE_SYNC.getUser(id);
            if(user != null){//valida si ya existe el username
                user.setEstado(0);
                user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar usuario", "El usuario no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede eliminar el usuario", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    @Override
    public boolean restore(String id){
        Log.setLog(className,Log.getReg());
        User user =  null;
        if(GlobalValues.isOnline()){
            user =  (User)GlobalValues.REMOTE_SYNC.getUser(id);
            if(user != null){//valida si ya existe el username
                user.setEstado(1);
                user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede restaurar usuario", "El usuario no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede restaurar el usuario", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public void sincronize() {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                for (User object : GlobalValues.REMOTE_SYNC.users(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
                for (User object : GlobalValues.LOCAL_SYNC.users(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }else{
                for (User object : GlobalValues.LOCAL_SYNC.users(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object get(String idObject) {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getUser(idObject);
    }
    
   
    
}
