/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author jlleivas
 */
public class CuotasConvenio extends SyncStringId{
    private Date fecha;
    private Date fechaPagado;
    private int monto;
    private int idConvenio;

    public CuotasConvenio() {
    }

    public CuotasConvenio(String cod,Date fecha, Date fechaPagado, int monto, int idConvenio, int estado, Date lastUpdate,int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setFechaPagado(fechaPagado);
        setMonto(monto);
        setIdConvenio(idConvenio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public Date getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }
}
