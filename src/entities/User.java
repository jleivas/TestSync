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
public class User {
    private int id;
    private String nombre;
    private String username;
    private String pass;
    private int tipo;
    private int estado;
    private Date lastUpdate;

    public User() {
    }

    public User(int id, String nombre, String username, String pass, int tipo, int estado, Date lastUpdate) {
        setId(id);
        setNombre(nombre);
        setUsername(username);
        setPass(pass);
        setTipo(tipo);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getPass() {
        return pass;
    }

    public int getTipo() {
        return tipo;
    }

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "\nID: "+this.id+
                " - Nombre: "+this.nombre+
                " - USERNAME: "+this.username+
                " - PASS: "+this.pass+
                " - TIPO:"+this.tipo+
                " - LAST_UPDATE:"+fn.date.Cmp.dateToString(this.lastUpdate, "dd-mm-yyyy")+
                " - Estado:"+this.estado;
    }
    
    
}
