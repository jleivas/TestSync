/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.abstractclasses;

import fn.date.Cmp;
import java.util.Date;

/**
 *
 * @author jlleivas
 */
public abstract class SyncIntId extends SyncClass{
    private int id;
    

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
