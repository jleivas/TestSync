/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import com.toedter.calendar.JDateChooser;
import dao.Dao;
import entities.Convenio;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.TipoPago;
import entities.abstractclasses.SyncIntId;
import entities.abstractclasses.SyncStringId;
import entities.context.SalesReportFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.OptionPane;
import static fn.ValidaRut.validarRut;
import fn.date.Cmp;
import fn.mail.Send;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesFunctions {
    private static String TIPO_PAGO_NO_REGISTRADO = "No registrado";
    private static int POS_MONTO = 0;
    private static int POS_NAME = 1;
    private static int POS_DATE = 2;
    private static int TAMANO_COLUMN_ABONOS = 3;//la columna dos corresponde a la descripcion del tipo de pago, se usa en funciones para imprimir
    
    public static String dateToString(Date date,String format){
        return Cmp.dateToString(date, format);
    }
    
    public static String getToName(String param){
        String[] str = getStr(param).toLowerCase().split(" ");
        StringBuffer value = new StringBuffer();
        for (String temp : str) {
            if(temp.length() > 1){
                value.append(Character.toUpperCase(temp.charAt(0))).append(temp.substring(1)).append(" ");
            }else{
                value.append(temp.toUpperCase()).append(" ");
            }
        }
        return value.toString().trim();
    }
    
    public static String getClassName(Object type){
        if(type == null) return "[...]";
        String name = (type.getClass().getName().contains("."))?
                type.getClass().getName().substring(type.getClass().getName().lastIndexOf(".")+1):type.getClass().getName();
        return name;
    }
    
    public static String getStr(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        else
            return arg.trim();
    }
    
    public static void emptyTable(JComboBox cbo, JTextField txt, String registry){
        String end = "os";
        registry = (!registry.endsWith("s"))? registry+"s":registry;
        end = (registry.endsWith("as"))? "as":end;
        if(txt.getText().length() > 1){
            OptionPane.showMsg("No existen "+registry, "No existen registros disponibles que contengan la palabra \""+txt.getText()+"\"",1);
        }else{
            if(registry.toLowerCase().contains("fichas")){
                if(cbo.getSelectedIndex() == 5){
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" eliminad"+end+".",1);
                }else{
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" registrad"+end+".",1);
                }
            }else{
                if(cbo.getSelectedIndex() == 0){
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" registrad"+end+".",1);
                }else{
                    OptionPane.showMsg("No existen "+registry, "No existen "+registry+" eliminad"+end+".",1);
                }
            }
        }
    }
    
    public static String strToPrice(int monto){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        return "$ "+formateador.format (monto);
    }
    
    public static int strToNumber(String arg){
        arg = GV.getStr(arg).replaceAll("[^0-9-]", "");
        boolean isNegative = (arg.startsWith("-"))? true:false;
        arg = arg.replaceAll("-", "").trim();
        if(arg.isEmpty())
            return 0;
        if(isNegative){
            return -Integer.parseInt(arg);
        }
        return Integer.parseInt(arg);
    }

    public static boolean strCompare(String str1, String str2) {
        str1 = str1.toLowerCase();
        str1 = str1.replaceAll(" ", "");
        str2 = str2.toLowerCase();
        str2 = str2.replaceAll(" ", "");
        return str1.equals(str2);
    }
    public static String formatoHora(int hora, int min) {
        String h = "";
        String m = "";
        if(hora < 10)
        h = "0";
        if(min < 10)
        m = "0";
            
        return h+hora+":"+m+min;
    }
    
    public static boolean containIntegrs(String arg){
        String temp = arg.trim().toLowerCase().replaceAll("[0-9]", "");
        if(!temp.equals(arg.toLowerCase()))
            return true;
        return false;
    }
    
    public static int contChar(char toCompare, String toCount){
        char[] arrayChar = toCount.toCharArray();
        int cont=0;
        for(int i=0; i<arrayChar.length; i++){

                if( arrayChar[i] == toCompare){
                    cont++;
                }
        }
        return cont;
    }
    
    public static int diasRestantes(String stFecha) throws ParseException{ 
            stFecha = getStr(stFecha).replaceAll("-", "/");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(!stFecha.isEmpty()){
                cal.setTime(sdf.parse(stFecha));
            }
            int dias = 0;
            if(cal.compareTo(Calendar.getInstance())>=0){
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{       
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                        if(dd.format(aux).equals(dd.format(fecha)))
                            activo = true; 
                        else
                            dias++;
                }while(activo != true);
            }else{
                Date fecha=cal.getTime();
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");       
                boolean activo = false;
                Calendar calendar; Date aux;
                do{      
                    calendar = Calendar.getInstance();           
                    calendar.add(Calendar.DAY_OF_YEAR, dias);
                    aux = calendar.getTime();
                        if(dd.format(aux).equals(dd.format(fecha)))
                            activo = true; 
                        else
                            dias--;
                }while(activo != true);
            }

            return dias; 
    }
    
    public static Date strToDate(String strFecha){
        //formato valido
        strFecha = getStr(strFecha);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {

        fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

        ex.printStackTrace();

        }
        
        return fecha;
    }

    public static boolean isCurrentDate(Date date) {
        if(GV.dateToString(date, "ddmmyyyy").equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return true;
        }
        return false;
    }

    public static int obtenerDescuentoFicha(Descuento descuento, int total) {
        int porc = 0;
        int dscto = 0;
        if(descuento != null){
            if(descuento.getPorcetange() > 0){
                porc = descuento.getPorcetange();
                dscto = (total * porc)/100;
            }else{
                dscto = descuento.getMonto();
            }
        }
        return GV.roundPrice(dscto);
    }

    public static void compileJCalendar(JDateChooser jDate) {
        if(GV.getStr(jDate.getDateFormatString().replaceAll("[0-9-]", "")).isEmpty()){
            return;
        }else{
            jDate.setDate(null);
        }
//        jDate.setDateFormatString(jDate.getDateFormatString().replaceAll("[^0-9-]", ""));//filtra sólo numeros y guiones
    }

    public static String toStrHour(int hora, int min) {
        hora = (hora >24)? -1:hora;
        min = (min > 60)? -1:min;
        if(hora < 0 || min < 0){
            return "--:--";
        }
        String h = (hora < 10)? "0": "";
        String m = (min < 10)? "0":"";
        return h+hora+":"+m+min;
    }

    public static void setHourToFicha(JSpinner txth1, JSpinner txtm1, JSpinner txth2, JSpinner txtm2) {
        int h1 = GV.strToNumber(GV.getStr(txth1.getValue().toString()));
        int m1 = GV.strToNumber(GV.getStr(txtm1.getValue().toString()));
        
        int h2 = GV.strToNumber(GV.getStr(txth2.getValue().toString()));
        int m2 = GV.strToNumber(GV.getStr(txtm2.getValue().toString()));
        
        int temp = 0;
        if(h2 < h1){
            temp = h1;
            h1 = h2;
            h2=temp;
        }
        if(h1 == h2 && m2 < m1){
            temp = m1;
            m1=m2;
            m2=temp;
        }
        
        String arg = toStrHour(h1, m1);
        arg = (arg.equals("--:--"))? "":arg+" - ";
        arg = (arg + toStrHour(h2, m2));
        GV.getFicha().setHoraEntrega(GV.getStr(arg.replaceAll("- --:--", "").trim()));
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    /**
     * debuelbe un arreglo de objetos de tipo string donde [monto][tipoPago][fecha]
     * @param codFicha
     * @return 
     */
    public static Object[][] listarAbonos(String codFicha) {
        Dao load = new Dao();
        
        List<Object> lista = load.listar(GV.convertFichaIdParamToListAbonos(codFicha), new HistorialPago());
        if(lista.size() == 0){return null;}
        String[][] abonos = new String[lista.size()][TAMANO_COLUMN_ABONOS];
        int i = 0;
        
        TipoPago tp = null;
        
        for (Object object : lista) {
            HistorialPago temp = (HistorialPago)object;
            try {
                tp = (TipoPago)load.get(null, temp.getIdTipoPago(), new TipoPago());
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(GlobalValuesPrint.class.getName()).log(Level.SEVERE, null, ex);
                tp = null;
            }
            abonos[i][POS_MONTO] = GV.strToPrice(temp.getAbono());
            
            abonos[i][POS_NAME] = (tp!=null)? tp.getNombre():TIPO_PAGO_NO_REGISTRADO;
            
            abonos[i][POS_DATE] = GV.dateToString(temp.getFecha(), "dd/mm/yyyy");
            i++;
        }
        return abonos;
    }
    
    public static int abonoMontoFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?strToNumber((String)arrayAbonos[index][POS_MONTO]):0;
    }
    
    public static Date abonoDateFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?strToDate((String)arrayAbonos[index][POS_DATE]):null;
    }
    
    public static String abonoNameFromArray(int index,Object[][] arrayAbonos){
        return(arrayAbonos.length >index)?((String)arrayAbonos[index][POS_NAME]):"";
    }

    public static int roundPrice(int price) {
        String temp = ""+price;
        int lastN = GV.strToNumber(temp.substring(temp.length()-1));
        return (lastN > 5)? (price-lastN)+10:price-lastN;
    }
    
    public static SalesReportFicha reportSalesObtain(List<Object> listFicha){
        SalesReportFicha srf = new SalesReportFicha(listFicha);
        return srf;
    }
    
    public static Object searchByIdInList(String code , List<Object> list, Object classType) {
        if(classType instanceof SyncIntId){
            Optional<Object> objectFound = list.stream()
            .filter(p -> ((SyncIntId)p).getId() == GV.strToNumber(code))
            .findFirst();
            return objectFound.isPresent() ? objectFound.get() : null;
        }
        if(classType instanceof SyncStringId){
            Optional<Object> objectFound = list.stream()
            .filter(p -> ((SyncStringId)p).getCod().equals(code))
            .findFirst();
            return objectFound.isPresent() ? objectFound.get() : null;
        }
        return null;
    }

    public static void spinnerNumberDisable(JSpinner spinnerNumber, int currentValue) {
        spinnerNumber.setModel(new SpinnerNumberModel(currentValue, currentValue, currentValue, 1));
        spinnerNumber.setValue(currentValue);
    }
    
    public static String formatRut(String rut) {
        if(rut == null || rut.isEmpty()){
            return "";
        }
        int cont = 0;
        String format;
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        format = "-" + rut.substring(rut.length() - 1);
        for (int i = rut.length() - 2; i >= 0; i--) {
            format = rut.substring(i, i + 1) + format;
            cont++;
            if (cont == 3 && i != 0) {
                format = "." + format;
                cont = 0;
            }
        }
        return format;
    }
    
    /**
     * Retorna un where en sql para listar fichas y todos sus datos, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio validara los userId o clientId
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static String getWhereFromFicha(Date dateTo, Date dateFrom,String idUser, String codClient, String idFicha){
        if(idFicha!=null){
            return "where f.fch_id = '"+idFicha+"'";
        }
        if(dateTo==null && dateFrom==null){
            if(idUser != null){
                return "where f.usuario_us_id = "+idUser+" and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
            }
            if(codClient != null){
                return "where f.cliente_cli_rut = '"+codClient+"' and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
            }
            return "where f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
        }
        dateTo=(dateTo==null)?dateFrom:dateTo;
        dateFrom=(dateFrom==null)?dateTo:dateFrom;
        if(Cmp.localIsNewOrEqual(dateTo, dateFrom)){
            Date aux = dateFrom;
            dateFrom=dateTo;
            dateTo=aux;
        }
        String d1 = (!GV.dateToString(dateTo, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateTo, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        String d2 = (!GV.dateToString(dateFrom, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateFrom, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        return "where f.fch_fecha BETWEEN '"+d1+"' and '"+d2+"' and f.fch_estado <> 0 ORDER BY f.fch_fecha DESC";
    }
    
    /**
     * Retorna un where en sql para listar todas las fichas incluidas las anuladas
     * y todos sus datos, si userId y clientCod son null,
     * buscara por fecha, 
     * de lo contratrio validara los userId o clientId
     * @param dateFrom
     * @param dateTo
     * @param idUser
     * @param codClient
     * @param idFicha 
     * @return 
     */
    public static String getWhereFromAllFichas(Date dateTo, Date dateFrom,String idUser, String codClient,String idConvenio, String idFicha){
        if(idFicha!=null){
            return "where f.fch_id = '"+idFicha+"'";
        }
        if(idConvenio != null){
            return "where f.convenio_cnv_id = "+idConvenio+" ORDER BY f.fch_fecha DESC";
        }
        if(dateTo==null && dateFrom==null){
            if(idUser != null){
                return "where f.usuario_us_id = "+idUser+" ORDER BY f.fch_fecha DESC";
            }
            if(codClient != null){
                return "where f.cliente_cli_rut = '"+codClient+"' ORDER BY f.fch_fecha DESC";
            }
            return "where f.fch_fecha ='"+GV.dateToString(new Date(), "yyyy-mm-dd")+"' ORDER BY f.fch_fecha DESC";
        }
        dateTo=(dateTo==null)?dateFrom:dateTo;
        dateFrom=(dateFrom==null)?dateTo:dateFrom;
        if(Cmp.localIsNewOrEqual(dateTo, dateFrom)){
            Date aux = dateFrom;
            dateFrom=dateTo;
            dateTo=aux;
        }
        String d1 = (!GV.dateToString(dateTo, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateTo, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        String d2 = (!GV.dateToString(dateFrom, "yyyy-mm-dd").equals("date-error"))?GV.dateToString(dateFrom, "yyyy-mm-dd"):GV.dateToString(new Date(), "yyyy-mm-dd");
        return "where f.fch_fecha BETWEEN '"+d1+"' and '"+d2+"' ORDER BY f.fch_fecha DESC";
    }
    
    public static void sendReportSalesMail(SalesReportFicha report, String email, String title){
        Send mail = new Send();
        mail.sendReportSalesMail(report, email, title);
    }
    
    /**
     * Devuelde una fecha de tipo Date con el resultado según parámetro sumaresta ingresado
     * @param fecha
     * @param sumaresta
     * @param opcion Parámetros validos: DAYS, MONTHS o YEARS.
     * @return 
     */
    public static Date SumaRestarFecha(Date fecha, int sumaresta, String opcion){
        if(fecha == null)return null;
        if(fecha instanceof java.sql.Date){
            fecha = new Date(fecha.getTime());
        }
        LocalDate date = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //Con Java9
        //LocalDate date = LocalDate.ofInstant(input.toInstant(), ZoneId.systemDefault());
        TemporalUnit unidadTemporal = null;
        switch(opcion){
            case "DAYS":
                unidadTemporal = ChronoUnit.DAYS;
                break;
            case "MONTHS":
                unidadTemporal = ChronoUnit.MONTHS;
                break;
            case "YEARS":
                unidadTemporal = ChronoUnit.YEARS;
                break;
            default:
                //Controlar error
        }
        LocalDate dateResultado = date.plus(sumaresta, unidadTemporal);
        return Date.from(dateResultado.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void createGuaranteeFicha(Ficha ficha) {
        Dao load = new Dao();
        load.createFicha(ficha, null);
        OptionPane.showMsg("Operación finalizada", "Se ha registrado una nueva garantía", 1);
    }

    public static boolean validaRut(String rut) {
        return validarRut(rut);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es igual
     * o superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOFutura(Date date){
        if(GV.dateToString(date, "ddmmyyyy")
                .equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return true;
        }
        return fechaFutura(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es
     * superior a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaFutura(Date date){
        return date.after(new Date());
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es inferior
     * a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaPasada(Date date){
        if(GV.dateToString(date, "ddmmyyyy")
                .equals(GV.dateToString(new Date(), "ddmmyyyy"))){
            return false;
        }
        return fechaActualOPasada(date);
    }
    
    /**
     * retorna true si la fecha ingresada por parametros es 
     * pasada o igual a la fecha actual
     * @param date
     * @return 
     */
    public static boolean fechaActualOPasada(Date date){
        return date.before(new Date());
    }
    
    public static void updateBDConvenioValidado(Convenio convenio){
        if(convenio.validate()){
            Dao load = new Dao();
            load.update(convenio);
            List<CuotasConvenio> listCuotas = convenio.getCuotasConvenio();
            if(listCuotas.size()>0){
                for (CuotasConvenio cc : listCuotas) {
                    try {
                        load.add(cc);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(GlobalValuesFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        OptionPane.showMsg("Error en BD", "Ocurrió un error al intentar guardar entidades\n"
                                + "CuotasConvenio de la clase Convenio ID:"+convenio.getId(), 3);
                    }
                }
            }
        }
    }
    
    public void convenioGenerarReporte(Convenio cnv){
        if(cnv.getEstado() == 2){
            
        }else{
            OptionPane.showMsg("No se puede generar el reporte", "El convenio debe estar generado,\n"
                    + "el sistema no adminte convenios anulados ni activos.", 2);
        }
    }
}
