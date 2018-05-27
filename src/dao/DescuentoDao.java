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
            if(!GlobalValues.REMOTE_SYNC.descuentoExist(temp.getNombre()))
                try {
                    return sync.Sync.add(GlobalValues.LOCAL_SYNC, GlobalValues.REMOTE_SYNC, GlobalValues.GLOBAL_SYNC, temp);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CristalDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            else{
                OptionPane.showMsg("No se puede crear nuevo descuento", "El nombre ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el nombre.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede crear nuevo descuento", "Para poder ingresar un nuevo registro debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean restore(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(String idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sincronize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
