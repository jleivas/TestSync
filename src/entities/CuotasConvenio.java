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
 * @author jlleivas
 */
public class CuotasConvenio extends SyncIntId{
    private Date fecha;
    private int monto;

    public CuotasConvenio() {
    }

    public CuotasConvenio(int id,Date fecha, int monto, int estado, Date lastUpdate,int lastHour) {
        setId(id);
        setFecha(fecha);
        setMonto(monto);
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
    
    
}
