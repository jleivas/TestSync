/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.OptionPane;
import fn.date.Cmp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesFunctions {
    
    public static String dateToString(Date date,String format){
        return Cmp.dateToString(format, format);
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
        if(registry.endsWith("as")){
            end = "as";
        }
        if(txt.getText().length() > 1){
            OptionPane.showMsg("No existen "+registry, "No existen registros disponibles que contengan la palabra \""+txt.getText()+"\"",1);
        }else{
            if(cbo.getSelectedIndex() == 0){
                OptionPane.showMsg("No existen "+registry, "No existen "+registry+" registrad"+end+".",1);
            }else{
                OptionPane.showMsg("No existen "+registry, "No existen "+registry+" eliminad"+end+".",1);
            }
        }
    }
    
    public static String strToPrice(int monto){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        return "$ "+formateador.format (monto);
    }
    
    public static int strToNumber(String arg){
        if(arg == null || arg.isEmpty())
            return 0;
        arg = arg.replaceAll("[^0-9]", "");
        if(arg.isEmpty())
            return 0;
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
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {

        fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

        ex.printStackTrace();

        }
        
        return fecha;
    }
}
