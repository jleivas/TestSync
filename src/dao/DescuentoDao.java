/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Descuento;
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
public class DescuentoDao implements Dao{
    private static String className="DescuentoDao";
    
    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        Descuento temp =  (Descuento)object;
        temp.setLastUpdate(new Date());
        if(GlobalValues.isOnline()){
            try {
                temp.setId(GlobalValues.REMOTE_SYNC.getMaxDescuentoId());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!GlobalValues.REMOTE_SYNC.descuentoExist(temp.getNombre())){
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
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
        Descuento des =  (Descuento)object;
        des.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        if(GlobalValues.isOnline()){
            Descuento old = (Descuento)GlobalValues.REMOTE_SYNC.getDescuento(des.getNombre());
            if(old == null){//no existe ningun registro con el mismo desname
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, des);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(old.getId() == des.getId()){//inserta sin validar desname
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, des);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                if(!GlobalValues.REMOTE_SYNC.descuentoExist(des.getNombre())){//valida si ya existe el nuevo desname
                    try {
                        return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, des);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
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
        Descuento des =  null;
        if(GlobalValues.isOnline()){
            des =  (Descuento)GlobalValues.REMOTE_SYNC.getDescuento(id);
            if(des != null){//valida si ya existe el desname
                des.setEstado(0);
                des.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, des);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede eliminar el registro", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean restore(String id) {
        Log.setLog(className,Log.getReg());
        Descuento des =  null;
        if(GlobalValues.isOnline()){
            des =  (Descuento)GlobalValues.REMOTE_SYNC.getDescuento(id);
            if(des != null){//valida si ya existe el desname
                des.setEstado(1);
                des.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, des);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede restaurar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede restaurar el registro", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public Object get(String idObject) {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getDescuento(idObject);
    }

    @Override
    public void sincronize() {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                for (Descuento object : GlobalValues.REMOTE_SYNC.descuentos(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
                for (Descuento object : GlobalValues.LOCAL_SYNC.descuentos(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }else{
                for (Descuento object : GlobalValues.LOCAL_SYNC.descuentos(-2)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DescuentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
