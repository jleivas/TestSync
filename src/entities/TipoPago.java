/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntId;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class TipoPago extends SyncIntId{
    private String nombre;

    public TipoPago() {
    }

    public TipoPago(int id, String nombre, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return getStr(nombre);
    }

    @Override
    public String toString() {
        return "tipoPago{" + "id=" + getId()+ ", nombre=" + nombre + '}';
    }
}
