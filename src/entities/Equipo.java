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
 * @author sdx
 */
public class Equipo extends SyncIntId{
    private String nombre;
    private String licencia;

    public Equipo(int id,String nombre, String licencia, int estado, Date lastUpdate, int lastHour) {
        setId(id);
        setNombre(nombre);
        setLicencia(licencia);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public Equipo(){
        
    }
    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return getToName(nombre);
    }

    public String getLicencia() {
        return licencia;
    }
    
}
