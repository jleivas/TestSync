/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import entities.Cristal;
import entities.User;
import fn.GlobalValues;
import fn.Log;
import fn.date.Cmp;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class Global implements SyncBd{
    private static String className="GbVlUser";

    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        if(object == null)
            return false;
        else{
            if(object instanceof User){
                for (User temp : GlobalValues.TMP_LIST_USERS) {
                    if(temp.getId() == ((User)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((User)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_USERS.indexOf(temp);
                            GlobalValues.TMP_LIST_USERS.add(index, (User)object);
                            GlobalValues.TMP_LIST_USERS.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_USERS.add((User)object);
            }
            if(object instanceof Cristal){
                for (Cristal temp : GlobalValues.TMP_LIST_CRISTAL) {
                    if(temp.getId() == ((Cristal)object).getId()){
                        if(!Cmp.localIsNewOrEqual(temp.getLastUpdate(), ((Cristal)object).getLastUpdate())){
                            int index = GlobalValues.TMP_LIST_CRISTAL.indexOf(temp);
                            GlobalValues.TMP_LIST_CRISTAL.add(index, (Cristal)object);
                            GlobalValues.TMP_LIST_CRISTAL.remove(index+1);
                            return true;
                        }else
                            return false;
                    }
                        
                }
                GlobalValues.TMP_LIST_CRISTAL.add((Cristal)object);
            }
        } 
        return true;
    }

    @Override
    public User getUser(String username) {
        Log.setLog(className,Log.getReg());
        for (User object : GlobalValues.TMP_LIST_USERS) {
            if((object.getUsername().toLowerCase()).equals(username.toLowerCase()))
                return object;
        }
        return null;
    }

    @Override
    public Cristal getCristal(String name) {
        Log.setLog(className,Log.getReg());
        for (Cristal object : GlobalValues.TMP_LIST_CRISTAL) {
            if((object.getNombre().toLowerCase()).equals(name.toLowerCase()))
                return object;
        }
        return null;
    }


}
