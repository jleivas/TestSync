/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import com.toedter.calendar.JDateChooser;
import dao.Dao;
import entities.Descuento;
import entities.TipoPago;
import entities.ficha.HistorialPago;
import fn.GV;
import fn.OptionPane;
import fn.date.Cmp;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesFunctions {
    
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
            cal.setTime(sdf.parse(stFecha));
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
    
    public static Object[][] listarAbonos(String codFicha) {
        Dao load = new Dao();
        int COLUMNAS = 2;//la columna dos corresponde a la descripcion del tipo de pago, se usa en funciones para imprimiry
        List<Object> lista = load.listar(codFicha, new HistorialPago());
        if(lista.size() == 0){return null;}
        String[][] abonos = new String[lista.size()][COLUMNAS];
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
            abonos[i][0] = GV.strToPrice(temp.getAbono());
            
            abonos[i][1] = (tp!=null)? tp.getNombre():"No registrado";
        }
        return abonos;
    }

    public static int roundPrice(int price) {
        String temp = ""+price;
        int lastN = GV.strToNumber(temp.substring(temp.length()-1));
        return (lastN > 5)? (price-lastN)+10:price-lastN;
    }
}
