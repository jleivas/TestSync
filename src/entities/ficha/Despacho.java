/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.SyncClass;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Despacho extends SyncClass{
    
    private String cod;
    private String rut;
    private String nombre;
    private Date fecha;
    private String idFicha;

    public Despacho() {
    }

    public Despacho(String cod, String rut, String nombre, Date fecha, String idFicha, int estado, Date lastUpdate) {
        this.cod = cod;
        this.rut = rut;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idFicha = idFicha;
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    public void setCod(String cod) {
        this.cod = cod;
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

    public String getCod() {
        return cod;
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

    @Override
    public String toString() {
        return "Despacho{" + "cod=" + cod 
                + ", rut=" + rut 
                + ", nombre=" + nombre 
                + ", fecha=" + fecha 
                + ", idFicha=" + idFicha + '}';
    }
}
