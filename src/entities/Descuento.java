/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Descuento {
    
    private int id;
    private String nombre;
    private String descripcion;
    private int porcetange;
    private int monto;
    private int estado;
    private Date lastUpdate;
    
    public Descuento (){}

    public Descuento(int id, String nombre, String descripcion, int porcetange, int monto, int estado, Date lastUpdate) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPorcetange(porcetange);
        setMonto(monto);
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    public int getMonto() {
        return monto;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public void setLastUpdate(Date lastUpdate) {
        if(lastUpdate == null)
            this.lastUpdate = new Date();
        else
            this.lastUpdate = lastUpdate;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
       
        this.nombre = nombre;
        
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPorcetange(int porcetange) {
        this.porcetange = porcetange;
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

    public String getDescripcion() {
        return descripcion;
    }

    public int getPorcetange() {
        return porcetange;
    }

    public int getEstado() {
        return estado;
    }
    
    public boolean isNumeric(){
        if(monto == 0)
            return false;
        else
            return true;
    }
    
    @Override
    public String toString() {
        return "Descuento{" + "id=" + id 
                + ", nombre=" + nombre+"\n"
                + ", descripcion="+ descripcion + "\n" 
                +", porcetange="+ porcetange +"\n" 
                + ", monto="+ monto +"\n"
                + ", estado="+ estado +"\n"
                + ", lastUdate="+ lastUpdate + '}';
    }
    
    
    
    
    
    
    
}
