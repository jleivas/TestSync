/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author sdx
 */
public class Inventario extends SyncStringId{
    private String nombre;
    private String descripcion;

    public Inventario() {
    }
    
    public Inventario(String cod,String nombre, String descripcion,int estado,Date lastUpdate, int lastHour) {
        setCod(cod);
        this.nombre = nombre;
        this.descripcion = descripcion;
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
