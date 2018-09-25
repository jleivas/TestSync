/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.abstractclasses.SyncIntId;
import fn.GV;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sdx
 */
public class Convenio extends SyncIntId{
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int cuotas;
    private Date fechaCobro;
    private int montoMaximo;
    private int montoPp;
    private int maximoClientes;
    private int idDescuento;
    private int porcentajeAdicion;
    private int idInstitucion;
    private List<CuotasConvenio> listCuotas = new ArrayList<CuotasConvenio>();

    public Convenio() {
    }

    public Convenio(int id,String nombre, Date fechaInicio, Date fechaFin, int cuotas,Date fechaCobro, int montoMaximo, int montoPp, int maximoClientes, int idDescuento, int porcentajeAdicion,int idInstitucion,int estado, Date lastUpdate,int lastHour) {
        setId(id);
        setNombre(nombre);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setCuotas(cuotas);
        setFechaCobro(fechaCobro);
        setMontoMaximo(montoMaximo);
        setMontoPp(montoPp);
        setMaximoClientes(maximoClientes);
        setIdDescuento(idDescuento);
        setPorcentajeAdicion(porcentajeAdicion);
        setIdInstitucion(idInstitucion);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void addCuotaConvenio(CuotasConvenio cuota){
        this.listCuotas.add(cuota);
    }
    
    public List<CuotasConvenio> getCuotasConvenio(){
        return this.listCuotas;
    }
    
    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    
    
    /**
     * retorna true si se encuentra activo para seguir
     * generando mas recetas oftalmologicas
     * @return 
     */
    public boolean activo(){
        if(getFechaInicio() == null || getFechaFin() == null)
            return false;
        if(GV.fechaActualOPasada(getFechaInicio()) && 
                GV.fechaActualOFutura(getFechaFin()))
            return true;
        return false;
    }

    public void setPorcentajeAdicion(int porcentajeAdicion) {
        this.porcentajeAdicion = porcentajeAdicion;
    }

    public int getPorcentajeAdicion() {
        return porcentajeAdicion;
    }

    public void setNombre(String nombre) {
        this.nombre = getToName(nombre);
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public void setMontoPp(int montoPp) {
        this.montoPp = montoPp;
    }

    public void setMaximoClientes(int maximoClientes) {
        this.maximoClientes = maximoClientes;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombre() {
        return getToName(nombre);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getCuotas() {
        return cuotas;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }

    public int getMontoPp() {
        return montoPp;
    }

    public int getMaximoClientes() {
        return maximoClientes;
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }
    
    
}
