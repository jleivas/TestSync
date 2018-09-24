/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entities.Convenio;
import entities.Institucion;
import entities.ficha.Ficha;
import fn.GV;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author sdx
 */
public class FichaConvenioRecursoDatos implements JRDataSource{
    private List<Ficha> thisFicha = new ArrayList<Ficha>();
    private String title="Reporte de recetas oftalmologicas";
    private String subtitle = GV.companyName();
    private String companyName = GV.companyName();
    private String web = GV.getOficinaWeb();
    private String dir = GV.getOficinaAddress();
    private String companyContacts = GV.getOficinaMail()+", "+GV.getOficinaPhone1()+" "+GV.getOficinaPhone2();
    private Institucion cliente = new Institucion();
    private Convenio thisConvenio = new Convenio();
    private java.util.Date nextFechaCobro;
    private class Resumen{
        public int montoTotal=0;
        int cuotas=0;
        int valorCuota=0;
        int totalAbono=0;
        int totalPendiente=0;
        String diasVencimiento = "";
    }
    private Resumen resumen = new Resumen();
    private int currentIndex = -1;
    @Override
    public boolean next() throws JRException {
        return ++currentIndex < thisFicha.size();
    }

    public void addTitle(String title, String subtitle){
        this.title = title;
        this.subtitle = subtitle;
    }
    
    public void addConvenio(Convenio convenio, Institucion cliente){
        this.thisConvenio = convenio;
        this.resumen.cuotas = convenio.getCuotas();
        this.resumen.diasVencimiento = "día "+GV.dateToString(convenio.getFechaCobro(), "dd")+" de cada mes";
        this.cliente = cliente;
        addNextFechaCobro(convenio.getFechaCobro());
    }
    
    private void addNextFechaCobro(java.util.Date date){
        if(GV.fechaActualOFutura(date)){
            this.nextFechaCobro = date;
            if(this.nextFechaCobro.after(GV.dateSumaResta(this.thisConvenio.getFechaCobro(), this.thisConvenio.getCuotas(), "MONTHS"))){
                this.nextFechaCobro = new java.util.Date();
            }
            return;
        }
        if(GV.fechaPasada(date)){
            addNextFechaCobro(GV.dateSumaResta(date, 1, "MONTHS"));
        }
    }
    
    public void addFicha(Ficha ficha){
        this.thisFicha.add(ficha);
        calcularResumen(ficha);
    }
    
    private void calcularResumen(Ficha ficha){
        this.resumen.montoTotal = this.resumen.montoTotal + ficha.getValorTotal()-ficha.getDescuento();
        
        this.resumen.totalAbono = this.resumen.totalAbono + ((ficha.getValorTotal()-ficha.getDescuento())-ficha.getSaldo());
        this.resumen.totalPendiente = this.resumen.montoTotal - this.resumen.totalAbono;
        this.resumen.valorCuota = GV.roundPrice(this.resumen.totalPendiente/this.resumen.cuotas);
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if("companyName".equals(jrf.getName())){
            valor = companyName;
        }
        if("web".equals(jrf.getName())){
            valor = web;
        }
        if("dir".equals(jrf.getName())){
            valor = dir;
        }
        if("companyContacts".equals(jrf.getName())){
            valor = companyContacts;
        }
        if("idConvenio".equals(jrf.getName())){
            valor = thisConvenio.getId();
        }
        if("nextFechaCobro".equals(jrf.getName())){
            valor = GV.dateToString(nextFechaCobro, "dd/mm/yyyy");
        }
        if("instName".equals(jrf.getName())){
            valor = cliente.getNombre();
        }
        if("instRut".equals(jrf.getName())){
            valor = "Ingrese rut o dni";
        }
        if("instMail".equals(jrf.getName())){
            valor = cliente.getEmail();
        }
        if("instPhone".equals(jrf.getName())){
            valor = cliente.getTelefono();
        }
        if("montoTotal".equals(jrf.getName())){
            valor = resumen.montoTotal;
        }
        if("cuotas".equals(jrf.getName())){
            valor = resumen.cuotas;
        }
        if("valorCuota".equals(jrf.getName())){
            valor = resumen.valorCuota;
        }
        if("totalAbono".equals(jrf.getName())){
            valor = resumen.totalAbono;
        }
        if("totalPendiente".equals(jrf.getName())){
            valor = resumen.totalPendiente;
        }
        if("diasVencimiento".equals(jrf.getName())){
            valor = resumen.diasVencimiento;
        }
        if("item".equals(jrf.getName())){
            valor = currentIndex+1;
        }
        if("rut".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getCod();
        }
        if("nombre".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getNombre();
        }
        if("telefono".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCliente().getTelefono1();
        }
        if("total".equals(jrf.getName())){
            valor = (thisFicha.get(currentIndex).getValorTotal()-thisFicha.get(currentIndex).getDescuento());
        }if("abono".equals(jrf.getName())){
            valor = (thisFicha.get(currentIndex).getValorTotal()-thisFicha.get(currentIndex).getDescuento())-thisFicha.get(currentIndex).getSaldo();
        }
        if("cuota".equals(jrf.getName())){
            valor = GV.roundPrice(thisFicha.get(currentIndex).getSaldo()/thisConvenio.getCuotas());
        }
        
        return valor;
    }
    
}
