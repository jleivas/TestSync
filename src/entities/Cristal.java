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
public class Cristal extends SyncClass{
    private String nombre;
    private int precio;

    public Cristal() {
    }

    public Cristal(int id, String nombre, int precio, int estado, Date lastUpdate) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        super.setLastUpdate(lastUpdate); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Date getLastUpdate() {
        return super.getLastUpdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public void setEstado(int estado) {
        super.setEstado(estado); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public int getEstado() {
        return super.getEstado(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return "\nID: "+getId()+
                " - Nombre: "+this.nombre+
                " - Precio: "+this.precio+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(getLastUpdate(), "dd-mm-yyyy")+
                " - Estado:"+getEstado();
    }
    
}
