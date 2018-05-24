/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import fn.GlobalValues;
import fn.OptionPane;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sync.Cmp;
import sync.Sync;
import sync.user.GbVlUser;
import sync.user.LcBdUser;
import sync.user.RmBdUser;

/**
 *
 * @author jorge
 */
public class UserDao implements Dao{
    private static GbVlUser global = new GbVlUser();
    private static LcBdUser local = new LcBdUser();
    private static RmBdUser remote = new RmBdUser();
    
    public boolean add(Object object){
        User user =  (User)object;
        user.setLastUpdate(new Date());
        if(Cmp.isOnline()){
            try {
                user.setId(remote.obtenerId());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!remote.exist(user.getUsername()))
                try {
                    return sync.Sync.add(local, remote, global, user);
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
        User user =  (User)object;
        user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(Cmp.isOnline()){
            User old = (User)remote.get(user.getUsername());
            if(old == null){//no existe ningun registro con el mismo username
                try {
                    return sync.Sync.add(local, remote, global, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(old.getId() == user.getId()){//inserta sin validar username
                try {
                    return sync.Sync.add(local, remote, global, user);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(!remote.exist(user.getUsername()))//valida si ya existe el nuevo username
                    try {
                        return sync.Sync.add(local, remote, global, user);
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
        User user =  null;
        if(Cmp.isOnline()){
            user =  (User)remote.get(id);
            if(user != null){//valida si ya existe el username
                user.setEstado(0);
                user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(local, remote, global, user);
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
    public void sincronize() {
        try {
            if(sync.Cmp.isOnline()){
                for (User object : remote.listar(-2)) {
                    sync.Sync.add(local, remote, global, object);
                }
                for (User object : local.listar(-2)) {
                    sync.Sync.add(local, remote, global, object);
                }
            }else{
                for (User object : local.listar(-2)) {
                    sync.Sync.add(local, remote, global, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object get(String idObject) {
        return local.get(idObject);
    }
    
   
    
}
