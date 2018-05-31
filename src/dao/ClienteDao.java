/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Cliente;
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
public class ClienteDao implements Dao{
    private static String className="ClienteDao";
    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        Cliente temp =  (Cliente)object;
        temp.setLastUpdate(new Date());
        if(GlobalValues.isOnline()){
            if(!GlobalValues.REMOTE_SYNC.clienteExist(temp.getRut())){
                return update(object);
            }else{
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            if(!GlobalValues.LOCAL_SYNC.clienteExist(temp.getRut())){
                return update(object);
            }else{
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        Log.setLog(className,Log.getReg());
        Cliente cli =  (Cliente)object;
        cli.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
        try {
            return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cli);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Log.setLog(className,Log.getReg());
        Cliente cli =  null;
        if(GlobalValues.isOnline()){
            cli =  (Cliente)GlobalValues.REMOTE_SYNC.getCliente(id);
            if(cli != null){//valida si ya existe el desname
                cli.setEstado(0);
                cli.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cli);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El rehistro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            cli =  (Cliente)GlobalValues.LOCAL_SYNC.getCliente(id);
            if(cli != null){//valida si ya existe el desname
                cli.setEstado(0);
                cli.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cli);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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
        Cliente cli =  null;
        if(GlobalValues.isOnline()){
            cli =  (Cliente)GlobalValues.REMOTE_SYNC.getCliente(id);
            if(cli != null){//valida si ya existe el desname
                cli.setEstado(1);
                cli.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cli);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            cli =  (Cliente)GlobalValues.LOCAL_SYNC.getCliente(id);
            if(cli != null){//valida si ya existe el desname
                cli.setEstado(1);
                cli.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, cli);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                OptionPane.showMsg("No se puede eliminar registro", "El registro no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }

    @Override
    public Object get(String idObject) {
        Log.setLog(className,Log.getReg());
        return GlobalValues.LOCAL_SYNC.getCliente(idObject);
    }

    @Override
    public void sincronize() {
        Log.setLog(className,Log.getReg());
        try {
            if(GlobalValues.isOnline()){
                for (Cliente object : GlobalValues.REMOTE_SYNC.clientes(GlobalValues.LAST_UPDATE)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
                for (Cliente object : GlobalValues.LOCAL_SYNC.clientes(GlobalValues.LAST_UPDATE)) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }else{
                for (Cliente object : GlobalValues.LOCAL_SYNC.clientes("-2")) {
                    sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, object);
                }
            }  
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
