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
public class Descuento extends SyncClass{
    
    private String nombre;
    private String descripcion;
    private int porcetange;
    private int monto;
    
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

    @Override
    public Date getLastUpdate() {
        return super.getLastUpdate(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public void setLastUpdate(Date lastUpdate) {
        super.setLastUpdate(lastUpdate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
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

    public String getDescripcion() {
        return descripcion;
    }

    public int getPorcetange() {
        return porcetange;
    }

    @Override
    public int getEstado() {
        return super.getEstado(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean isNumeric(){
        if(monto == 0)
            return false;
        else
            return true;
    }
    
    @Override
    public String toString() {
        return "Descuento{" + "id=" + getId()
                + ", nombre=" + nombre+"\n"
                + ", descripcion="+ descripcion + "\n" 
                +", porcetange="+ porcetange +"\n" 
                + ", monto="+ monto +"\n"
                + ", estado="+ getEstado() +"\n"
                + ", lastUdate="+ getLastUpdate() + '}';
    }
    
    
    
    
    
    
    
}
