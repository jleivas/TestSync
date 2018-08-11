/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.Cliente;
import entities.Convenio;
import entities.Doctor;
import entities.Institucion;
import entities.abstractclasses.SyncStringId;
import entities.User;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Ficha extends SyncStringId{
    
    private Date fecha;
    private Date fechaEntrega;
    private String lugarEntrega;
    private String horaEntrega;
    private String observacion;
    private int valorTotal;
    private int descuento;
    private int saldo;
   
    // referencias
    private Cliente cliente;
    private Doctor doctor;
    
    private Institucion institucion;
    private Despacho despacho;
    private Armazon lejos;
    private Armazon cerca;
    private User user;
    private Convenio convenio;

    public Ficha() {
    }

    public Ficha(String cod, Date fecha, Date fechaEntrega, String lugarEntrega, 
            String horaEntrega, String observacion, int valorTotal, int descuento, int saldo, 
            Cliente cliente, Doctor doctor,Institucion institucion, Despacho despacho , 
            Armazon lejos,Armazon cerca, User user, Convenio convenio, int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        setFecha(fecha);
        setFechaEntrega(fechaEntrega);
        setLugarEntrega(lugarEntrega);
        setHoraEntrega(horaEntrega);
        setObservacion(observacion);
        setValorTotal(valorTotal);
        setDescuento(descuento);
        setSaldo(saldo);
        
        setCliente(cliente);
        setDoctor(doctor);
        setInstitucion(institucion);
        setDespacho(despacho);
        setLejos(lejos);
        setCerca(cerca);
        setUser(user);
        setConvenio(convenio);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = getStr(lugarEntrega);
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = getStr(horaEntrega);
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setObservacion(String observacion) {
        this.observacion = getStr(observacion);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public void setDespacho(Despacho despacho) {
        this.despacho = despacho;
    }

    public void setLejos(Armazon lejos) {
        this.lejos = lejos;
    }

    public void setCerca(Armazon cerca) {
        this.cerca = cerca;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getObservacion() {
        return observacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public int getDescuento() {
        return descuento;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public Despacho getDespacho() {
        return despacho;
    }

    public Armazon getLejos() {
        return lejos;
    }

    public Armazon getCerca() {
        return cerca;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Ficha{" + "cod=" + getCod()+"\n"
                + ", fecha=" + fecha+"\n" 
                + ", fechaEntrega=" + fechaEntrega+"\n" 
                + ", lugarEntrega=" + lugarEntrega +"\n"
                + ", horaEntrega=" + horaEntrega +"\n"
                + ", valorTotal=" + valorTotal +"\n"
                + ", saldo=" + saldo+"\n" 
                + ", observacion=" + observacion +"\n"
                + ", cliente=" + cliente.toString()+"\n" 
                + ", doctor=" + doctor.toString()+"\n" 
                + ", descuento=" + descuento+"%"+"\n" 
                + ", institucion=" + institucion.toString()+"\n" 
                + ", despacho=" + despacho.toString()+"\n"
                + ", armazon lejos=" + lejos.toString()+"\n"
                + ", armazon cerca=" + cerca.toString()+"\n"
                + ", estado=" + getEstado()+"\n" 
                + ", lastUpdate=" + getLastUpdate()+"\n" 
                +'}';
    }
    
    

   
    
    
    
       
    
    
    
}
