/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import fn.OptionPane;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sdx
 */
public class Sync {
    /**
     * Add or update new objct in static variables, local data base and remote data base.
     * @param localData
     * @param remoteData
     * @param globalData
     * @param object
     * @param objectId
     * @return true if insert in static variables and local data base or remote data base, false if don't insert in static variables
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static boolean add(SyncBd localData, SyncBd remoteData, SyncBd globalData, Object object) throws SQLException, ClassNotFoundException{        
        System.out.println("Sync::add(SyncBd localData, SyncBd remoteData, SyncBd globalData, Object object)");    
        
        if(!globalData.add(object)){
            return false;
        }
        localData.add(object);
        if(sync.Cmp.isOnline()){
            remoteData.add(object);
        }
        return true;
        }

    
}
