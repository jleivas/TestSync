/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Doctor;
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
public class DoctorDao implements Dao{
    private static String className="DoctorDao";
    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        Doctor temp =  (Doctor)object;
        temp.setLastUpdate(new Date());
        if(GlobalValues.isOnline()){
            if(!GlobalValues.REMOTE_SYNC.doctorExist(temp.getRut())){
                return update(object);
            }else{
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            if(!GlobalValues.LOCAL_SYNC.doctorExist(temp.getRut())){
                return update(object);
            }else{
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        Doctor temp =  (Doctor)object;
        temp.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        try {
            return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Log.setLog(className,Log.getReg());
        Doctor temp =  null;
        if(GlobalValues.isOnline()){
            temp =  (Doctor)GlobalValues.REMOTE_SYNC.getDoctor(id);
            if(temp != null){//valida si ya existe el desname
                temp.setEstado(0);
                temp.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El rehistro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            temp =  (Doctor)GlobalValues.LOCAL_SYNC.getDoctor(id);
            if(temp != null){//valida si ya existe el desname
                temp.setEstado(0);
                temp.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El rehistro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    @Override
    public boolean restore(String id) {
        Log.setLog(className,Log.getReg());
        Doctor temp =  null;
        if(GlobalValues.isOnline()){
            temp =  (Doctor)GlobalValues.REMOTE_SYNC.getDoctor(id);
            if(temp != null){//valida si ya existe el desname
                temp.setEstado(1);
                temp.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El rehistro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            temp =  (Doctor)GlobalValues.LOCAL_SYNC.getDoctor(id);
            if(temp != null){//valida si ya existe el desname
                temp.setEstado(1);
                temp.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El rehistro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    @Override
    public Object get(String idObject) {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getDoctor(idObject);
    }

    @Override
    public void sincronize() {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                for (Doctor object : GlobalValues.REMOTE_SYNC.doctores(GlobalValues.LAST_UPDATE)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
                for (Doctor object : GlobalValues.LOCAL_SYNC.doctores(GlobalValues.LAST_UPDATE)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }else{
                for (Doctor object : GlobalValues.LOCAL_SYNC.doctores("-2")) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
