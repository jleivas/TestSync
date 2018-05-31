/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Despacho {
    
    private String id;
    private String rut;
    private String nombre;
    private Date fecha;
    private String idFicha;
    private int estado;
    private Date lastUpdate;

    public Despacho() {
    }

    public Despacho(String id, String rut, String nombre, Date fecha, String idFicha, int estado, Date lastUpdate) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idFicha = idFicha;
        this.estado = estado;
        setLastUpdate(lastUpdate);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }

    public String getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getIdFicha() {
        return idFicha;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setLastUpdate(Date lastUpdate) {
        if(lastUpdate == null)
            this.lastUpdate = new Date();
        else
            this.lastUpdate = lastUpdate;
    }

    public int getEstado() {
        return estado;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    

    @Override
    public String toString() {
        return "Despacho{" + "id=" + id 
                + ", rut=" + rut 
                + ", nombre=" + nombre 
                + ", fecha=" + fecha 
                + ", idFicha=" + idFicha + '}';
    }
    
    
    
}
