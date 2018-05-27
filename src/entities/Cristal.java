/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author home
 */
public class Cristal {
    private int id;
    private String nombre;
    private int precio;
    private int estado;
    private Date lastUpdate;

    public Cristal() {
    }

    public Cristal(int id, String nombre, int precio, int estado, Date lastUpdate) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    public void setLastUpdate(Date lastUpdate) {
        if(lastUpdate == null)
            this.lastUpdate = new Date();
        else
            this.lastUpdate = lastUpdate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getEstado() {
        return estado;
    }
    
    @Override
    public String toString() {
        return "\nID: "+this.id+
                " - Nombre: "+this.nombre+
                " - Precio: "+this.precio+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(this.lastUpdate, "dd-mm-yyyy")+
                " - Estado:"+this.estado;
    }
    
}
