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

    public InternMail(int id, User remitente, User destinatario, String asunto, String contenido, int estado, Date fecha, String hora) {
        setId(id);
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
        setEstado(estado);
        this.fecha = fecha;
        this.hora = hora;
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
}
