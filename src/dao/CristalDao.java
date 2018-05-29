/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Cristal;
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
 * @author sdx
 */
public class CristalDao implements Dao {
    private static String className = "CristalDao";

    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        Cristal cristal =  (Cristal)object;
        cristal.setLastUpdate(new Date());
        if(GlobalValues.isOnline()){
            try {
                cristal.setId(GlobalValues.REMOTE_SYNC.getMaxCristalId());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!GlobalValues.REMOTE_SYNC.cristalExist(cristal.getNombre())){
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede crear nuevo registro", "El nombre ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el nombre.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede crear nuevo registro", "Para poder ingresar un nuevo registro debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        Cristal cristal =  (Cristal)object;
        cristal.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(GlobalValues.isOnline()){
            Cristal old = (Cristal)GlobalValues.REMOTE_SYNC.getCristal(cristal.getNombre());
            if(old == null){//no existe ningun registro con el mismo cristalname
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(old.getId() == cristal.getId()){//inserta sin validar cristalname
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(!GlobalValues.REMOTE_SYNC.cristalExist(cristal.getNombre())){//valida si ya existe el nuevo cristalname
                    try {
                        return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    OptionPane.showMsg("No se puede actualizar registro", "El nombre ya se encuentra utilizado,\n"
                            + "Para poder ingresar un nuevo registro debes cambiar el nombre.", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else{
            OptionPane.showMsg("No se puede actualizar el registro", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Log.setLog(className,Log.getReg());
        Cristal cristal =  null;
        if(GlobalValues.isOnline()){
            cristal =  (Cristal)GlobalValues.REMOTE_SYNC.getCristal(id);
            if(cristal != null){//valida si ya existe el cristalname
                cristal.setEstado(0);
                cristal.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar cristal", "El cristal no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede eliminar el cristal", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean restore(String id) {
        Log.setLog(className,Log.getReg());
        Cristal cristal =  null;
        if(GlobalValues.isOnline()){
            cristal =  (Cristal)GlobalValues.REMOTE_SYNC.getCristal(id);
            if(cristal != null){//valida si ya existe el cristalname
                cristal.setEstado(1);
                cristal.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cristal);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede restaurar cristal", "El cristal no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede restaurar el cristal", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public Object get(String idObject) {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getCristal(idObject);
    }

    @Override
    public void sincronize() {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                for (Cristal object : GlobalValues.REMOTE_SYNC.cristales(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
                for (Cristal object : GlobalValues.LOCAL_SYNC.cristales(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }else{
                for (Cristal object : GlobalValues.LOCAL_SYNC.cristales(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
