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
public class HistorialPago extends SyncClass{
    
    private String cod;
    private Date fecha;
    private int abono;
    private int idTipoPago;
    private String idFicha;
    

    public HistorialPago() {
    }

    public HistorialPago(String cod, Date fecha, int abono, int estado, int tipoPago, String idFicha, Date lastUpdate) {
        setCod(cod);
        setFecha(fecha);
        setAbono(abono);
        setIdFicha(idFicha);
        setIdTipoPago(tipoPago);
        setEstado(estado);
        setLastUpdate(lastUpdate);
    }

    

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }
    
    public String getCod() {
        return cod;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getAbono() {
        return abono;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }  
    
    public String getIdFicha() {
        return idFicha;
    }
    
    @Override
    public String toString() {
        return "Despacho{" + "cod=" + cod 
                + ", fecha=" + fecha 
                + ", abono=" + abono 
                + ", idTipoPago=" + idTipoPago 
                + ", idFicha=" + idFicha 
                + ", estado=" + getEstado() 
                + ", lastUpdate=" + getLastUpdate() + '}';
    }
}
