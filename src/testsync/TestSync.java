/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import fn.print.jcPrint;
import dao.Dao;
import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Lente;
import entities.Oficina;
import entities.TipoPago;
import entities.User;
import fn.GV;
import fn.SubProcess;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.mail.Send;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sync.entities.Local;

/**
 *
 * @author jorge
 */
public class TestSync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException, IOException, InstantiationException, IllegalAccessException{
        String vdd = "$ 14.500";
        System.out.println(GV.strToNumber(vdd));
    }
    
    private static boolean isNumber(String arg){
        arg = arg.replaceAll("[^0-9]", "");
        if(arg.isEmpty())
            return false;
        return true;
    }
    public static String getDescuentoName(String arg){
        arg = GV.getStr(arg);
        if(arg.contains("(") && !arg.startsWith("(")){
            arg=arg.substring(0,arg.indexOf("(")-1);
        }
        return arg.trim();
    }
    
    public static String getUserName(String arg){
        if(arg == null || arg.replaceAll(" ", "").isEmpty())
            return "";
        arg = arg.trim();
        if(arg.contains("<") && !arg.endsWith("<")){
            arg=arg.substring(arg.indexOf("<")+1).replaceAll(">", "");
        }
        return arg;
    }
    
    public static String getToName(String param){
        String[] str = param.trim().split(" ");
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
    private static ArrayList<String> listar(){
        ArrayList<String> rutDoctores = new ArrayList<>();
        rutDoctores.add("1-0");
        rutDoctores.add("2-0");
        rutDoctores.add("3-0");
        rutDoctores.add("4-0");
        rutDoctores.add("5-0");
        rutDoctores.add("6-0");
        return rutDoctores;
        
    }
    private static int getNumbers(String arg){
        if(arg == null || !arg.contains("-"))
            return -1;
        String[] temp = arg.split("-");
        arg = temp[0].replaceAll("[^0-9]", "");
        if(arg.isEmpty())
            return -1;
        return Integer.parseInt(arg)-1;
    }
    
    private static int strToNumber(String arg){
        if(arg == null || arg.isEmpty())
            return 0;
        arg = arg.replaceAll("[^0-9]", "");
        if(arg.isEmpty())
            return 0;
        return Integer.parseInt(arg);
    }
    
    
}
