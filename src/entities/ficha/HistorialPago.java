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
public class HistorialPago {
    
    private String id;
    private Date fecha;
    private int abono;
    private int estado;
    private int idTipoPago;
    private String idFicha;
    private Date lastUpdate;
    

    public HistorialPago() {
    }

    public HistorialPago(String id, Date fecha, int abono, int estado, int tipoPago, String idFicha, Date lastUpdate) {
        setId(id);
        setFecha(fecha);
        setAbono(abono);
        setIdFicha(idFicha);
        setIdTipoPago(tipoPago);
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    public String getIdFicha() {
        return idFicha;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }
    
    

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        if(lastUpdate == null)
            this.lastUpdate = new Date();
        else
            this.lastUpdate = lastUpdate;
    }


    public String getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAbono() {
        return abono;
    }

    public int getEstado() {
        return estado;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }  
    
    @Override
    public String toString() {
        return "Despacho{" + "id=" + id 
                + ", fecha=" + fecha 
                + ", abono=" + abono 
                + ", estado=" + estado 
                + ", idTipoPago=" + idTipoPago 
                + ", idFicha=" + idFicha 
                + ", lastUpdate=" + lastUpdate + '}';
    }
}
