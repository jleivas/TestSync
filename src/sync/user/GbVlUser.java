/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.user;

import entities.User;
import fn.GlobalValues;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class GbVlUser implements SyncBd{


    @Override
    public boolean add(Object old,Object object) {
        if(object == null)
            return false;
        if(old == null)
            GlobalValues.TMP_LIST_USERS.add((User)object);
        else{
            int index = GlobalValues.TMP_LIST_USERS.indexOf((User)old);
            if(index >= 0){
                GlobalValues.TMP_LIST_USERS.add(index, (User)object);
                GlobalValues.TMP_LIST_USERS.remove((User)old);
            }else{
                GlobalValues.TMP_LIST_USERS.add((User)object);
            }
        } 
        return true;
    }

    @Override
    public Object get(String objectId) {
        for (User object : GlobalValues.TMP_LIST_USERS) {
            if((""+object.getId()).equals(""+objectId) )
                return object;
        }
        return null;
    }


}
