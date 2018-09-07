/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import entities.ficha.Ficha;
import fn.GV;
import fn.globalValues.GlobalValuesPrint;
import static fn.globalValues.GlobalValuesPrint.descuentoFormatPrint;
import static fn.globalValues.GlobalValuesPrint.getPrecioCristal;
import static fn.globalValues.GlobalValuesPrint.getPrecioLente;
import static fn.globalValues.GlobalValuesPrint.obtenerAbonos;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author sdx
 */
public class FichaDataSource implements JRDataSource{
    private List<ReportFicha> thisFicha = new ArrayList<ReportFicha>();
    private int currentIndex = -1;
    @Override
    public boolean next() throws JRException {
        return ++currentIndex < thisFicha.size();
    }

    public void addFicha(Ficha ficha){
        ReportFicha rpf;
        String e1 = (ficha.getLejos().getEndurecido()==1)?"SI":"NO";
        String c1 = (ficha.getLejos().getCapa()==1)?"SI":"NO";
        String p1 = (ficha.getLejos().getPlusMax()==1)?"SI":"NO";
        String e2 = (ficha.getCerca().getEndurecido()==1)?"SI":"NO";
        String c2 = (ficha.getCerca().getCapa()==1)?"SI":"NO";
        String p2 = (ficha.getCerca().getPlusMax()==1)?"SI":"NO";
        int precioLente1 = getPrecioLente(ficha.getCerca().getMarca());
        int precioLente2 = getPrecioLente(ficha.getLejos().getMarca());
        int precioCristal1 = getPrecioCristal(ficha.getCerca().getCristal());
        int precioCristal2 = getPrecioCristal(ficha.getLejos().getCristal());
        String total = GV.strToPrice(ficha.getValorTotal()-ficha.getDescuento());
        String abonos = obtenerAbonos(ficha.getCod());
        String saldo = GV.strToPrice(ficha.getSaldo());
        rpf = new ReportFicha(GV.dateToString(ficha.getFecha(), "dd/mm/yyyy"),
                ficha.getCod(), ficha.getLugarEntrega(), 
                GV.dateToString(ficha.getFechaEntrega(), "dd/mm/yyyy")+" "+ficha.getHoraEntrega(),
                ficha.getCliente().getNombre(), GlobalValuesPrint.obtenerFormatoCliente(ficha.getCliente()), 
                ficha.getCliente().getDireccion(), ficha.getCliente().getComuna(), 
                total, 
                abonos, saldo,
                ficha.getLejos().getMarca(), ficha.getLejos().getCristal(), ficha.getLejos().getOdEsf(), 
                ficha.getLejos().getOdCil(), ficha.getLejos().getOdA(), ficha.getLejos().getOiEsf(), 
                ficha.getLejos().getOiCil(), ficha.getLejos().getOiA(), ""+ficha.getLejos().getDp(), e1, c1, p1,
                ficha.getCerca().getMarca(),
                ficha.getCerca().getCristal(), ficha.getCerca().getAdd(), ficha.getCerca().getOdEsf(),
                ficha.getCerca().getOdCil(), ficha.getCerca().getOdA(), ficha.getCerca().getOiEsf(), 
                ficha.getCerca().getOiCil(), ficha.getCerca().getOiA(), ""+ficha.getCerca().getDp(), e2, c2, p2,
                ficha.getObservacion(), GV.companyName(), GV.getOficinaWeb(), GV.getOficinaAddress()+"-"+GV.getOficinaCity(),
                GV.getOficinaMail(), GV.getOficinaPhone1()+"-"+GV.getOficinaPhone2(), 
                GV.strToPrice(precioLente1), GV.strToPrice(precioCristal1), 
                GV.strToPrice(precioLente2), GV.strToPrice(precioCristal2), 
                total,descuentoFormatPrint(ficha.getDescuento()), abonos, saldo,
                "Servicios informaticos",
                "17.665.703-0","Desarrollo de software","Garantía de 3 meses, no cubre daños provocados por el cliente");
        this.thisFicha.add(rpf);
    }
    
    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        
        if("date".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDate();
        }
        if("folio".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getFolio();
        }
        if("place".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getPlace();
        }
        if("dateHour".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDateHour();
        }
        if("name".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getName();
        }
        if("phone".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getPhone();
        }
        if("address".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getAddress();
        }
        if("comuna".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getComuna();
        }if("total".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getTotal();
        }
        if("abono".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getAbono();
        }
        if("saldo".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getSaldo();
        }
        if("mar1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getMar1();
        }if("cri1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCri1();
        }if("ode1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOde1();
        }if("odc1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOdc1();
        }if("oda1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOda1();
        }if("oie1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOie1();
        }if("oic1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOic1();
        }if("oia1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOia1();
        }if("dp1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDp1();
        }if("e1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getE1();
        }if("c1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getC1();
        }if("p1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getP1();
        }
        if("mar2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getMar2();
        }if("cri2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCri2();
        }
        if("add2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getAdd2();
        }if("ode2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOde2();
        }if("odc2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOdc2();
        }if("oda2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOda2();
        }if("oie2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOie2();
        }if("oic2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOic2();
        }if("oia2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOia2();
        }if("dp2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDp2();
        }if("e2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getE2();
        }if("c2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getC2();
        }if("p2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getP2();
        }
        if("ob".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getOb();
        }if("companyName".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCompanyName();
        }if("web".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getWeb();
        }if("dir".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDir();
        }if("mail".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getMail();
        }if("phones".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getPhones();
        }if("vm1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getVm1();
        }if("vc1".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getVc1();
        }
        if("vm2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getVm2();
        }if("vc2".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getVc2();
        }if("totales".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getTotales();
        }if("abonos".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getAbonos();
        }if("saldos".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getSaldos();
        }
        if("companyPronombre".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCompanyPronombre();
        }
        if("companyRut".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCompanyRut();
        }
        if("companyGiro".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getCompanyGiro();
        }
        if("defaultMsg".equals(jrf.getName())){
            valor = thisFicha.get(currentIndex).getDefaultMsg();
        }
        
        return valor;
    }
    
}
