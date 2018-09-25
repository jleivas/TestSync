/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import fn.print.jcPrint;
import dao.Dao;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Lente;
import entities.Oficina;
import entities.TipoPago;
import entities.User;
import entities.context.SalesReportFicha;
import entities.ficha.Ficha;
import fn.GV;
import fn.OptionPane;
import fn.SubProcess;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesBD;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesXmlFiles;
import fn.mail.Send;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sync.entities.Local;

/**
 *
 * @author jorge
 */
public class TestSync {

    private static String ID_PARAM_IS_USER = "USER/";
    private static String ID_PARAM_IS_CLIENT = "CLIENT/";
    private static Dao load = new Dao();
    private static List<Object> lista = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException, IOException, InstantiationException, IllegalAccessException{
        lista = GlobalValuesBD.listarAllFichas(null, null, "1", null, null, null);
//        GV.excelExportFichas(lista);
        Date fechaInicio = GV.strToDate("01-09-2018");
        Date fechaFin = GV.strToDate("01-09-2018");
        Date fechaCobro = GV.strToDate("02-09-2018");
        int porcentaje =0;
        int estado = 1;
        Convenio test = new Convenio(1, "convenio test", fechaInicio, fechaFin, 3, fechaCobro, 0, 0, 0, 0, porcentaje, 1, estado, null, 0);
        test = validateConvenio(test);
        System.out.println(test.toString());
        int i = 0;
        for (CuotasConvenio cc : test.getCuotasConvenio()) {
            i++;
            System.out.println("************CUOTAS "+i+"  **************");
            System.out.println(cc.toString());
            System.out.println("****************************************");
        }
    }
    public static boolean fichaIdParamIsUSer(String arg){
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_USER)) ? true:false;
    }
    
    public static boolean fichaIdParamIsClient(String arg){
        return (GV.getStr(arg).startsWith(ID_PARAM_IS_CLIENT)) ? true:false;
    }
    
    public static String convertFichaIdParamToUSer(String arg){
        return ID_PARAM_IS_USER+GV.getStr(arg).replaceAll(ID_PARAM_IS_CLIENT, "");
    }
    
    public static String convertFichaIdParamToClient(String arg){
        return ID_PARAM_IS_CLIENT+GV.getStr(arg).replaceAll(ID_PARAM_IS_USER, "");
    }
    
    public static Convenio validateConvenio(Convenio convenio){
        if(convenio.getEstado() == 1){
            if(GV.fechaPasada(convenio.getFechaFin())){
//                lista = GlobalValuesBD.getFichasByConveny(convenio.getId());
                if(lista.isEmpty()){
                    convenio.setFechaFin(GV.dateSumaResta(convenio.getFechaFin(), 1,"DAYS"));
                    if(GV.dateToString(convenio.getFechaFin(), "ddmmyyyy")
                            .equals(GV.dateToString(convenio.getFechaCobro(), "ddmmyyyy"))){
                        convenio.setFechaCobro(GV.dateSumaResta(convenio.getFechaCobro(), 1, "MONTHS"));
                    }
                }else{
                    int totalPendiente = 0;
                    for (Object object : lista) {
                        totalPendiente = totalPendiente + ((Ficha)object).getSaldo();
                    }
                    for (int i = 0; i < convenio.getCuotas(); i++) {
                        convenio.addCuotaConvenio(
                                new CuotasConvenio(null, GV.dateSumaResta(convenio.getFechaCobro(), i, "MONTHS"),
                                null, GV.roundPrice((totalPendiente/convenio.getCuotas())), convenio.getId(),
                                1, null, 0)
                        );
                    }
                    convenio.setEstado(2);
                }
            }
        }
        return convenio;
    }
    
}
