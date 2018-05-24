/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.user;

import entities.User;
import fn.GlobalValues;
import fn.date.Cmp;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class GbVlUser implements SyncBd{


    @Override
    public boolean add(Object object) {
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
        } 
        return true;
    }

    @Override
    public Object get(String objectId) {
        for (User object : GlobalValues.TMP_LIST_USERS) {
            if((object.getUsername().toLowerCase()).equals(objectId.toLowerCase()))
                return object;
        }
        return null;
    }


}
