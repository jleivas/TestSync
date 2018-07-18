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
 * @author Vector
 */
public class InternMail extends SyncIntId{
    private User remitente;
    private User destinatario;
    private String asunto;
    private String contenido;
    private Date fecha;
    private String hora;

    public InternMail() {
    }

    public InternMail(int id, User remitente, User destinatario, String asunto, String contenido, Date fecha, String hora,int estado,Date lastUpdate,int lastHour) {
        setId(id);
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
        setEstado(estado);
        this.fecha = fecha;
        this.hora = hora;
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public User getRemitente() {
        return remitente;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public String getAsunto() {
        return getStr(asunto);
    }

    public String getContenido() {
        return getStr(contenido);
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return getStr(hora);
    }

    public void setRemitente(User remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(User destinatario) {
        this.destinatario = destinatario;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * 0: eliminado (No se visualizará en la vista),
     * 1: no leido
     * 2: leido
     * @param estado 
     */
    @Override
    public void setEstado(int estado) {
        super.setEstado(estado); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 0: eliminado (No se visualizará en la vista),
     * 1: no leido
     * 2: leido
     * @return 
     */
    @Override
    public int getEstado() {
        return super.getEstado(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
