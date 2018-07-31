/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import static fn.GV.dateToString;
import static fn.GV.getStr;
import static fn.GV.getToName;
import java.util.Date;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesVariables {
    
    /*  Nombres de sistema  */
    public static String PROJECTNAME="DCS Optics";
    public static String VERSION = "v4.0.0";
    public static String EQUIPO="jorge";//el nombre debe concatenarse con la fecha de instalacion
    public static int EQUIPO_ID = 1;
    public static String INVENTARIO_NAME = "INV";
    
    /* Seguridad */
    public static String SALT = "optidataodm4softdirex";
    public static String PASS;
    
    /* LICENCIA */
    public static String COMPANY_NAME = "DCS Optics";
    public static boolean LICENCE = true;
    public static String EXP_DATE = "00-00-0000";
    public static String API_URI;
    
    /* Update */
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    
    
    
    /* Variables del sistema */
    
    
    public static int ID_USER = 0;
    public static Date TMP_DATE_FROM = null;
    public static Date TMP_DATE_TO =null;
    
    public static void setInventarioLocal(String inventario){
        INVENTARIO_NAME = getStr(inventario);
    }
    
    public static void setCompanyName(String nombre) {
        COMPANY_NAME = getToName(nombre);
    }
    
    public static String getCompanyName(){
        return getStr(COMPANY_NAME);
    }

    public static String getProjectName() {
        return getStr(PROJECTNAME);
    }
    
    public static String getVersion(){
        return getStr(VERSION);
    }
    
    public static boolean getLicence(){
        return LICENCE;
    }
    
    public static String getExpDate(){
        return getStr(EXP_DATE);
    }

    public static String getInventarioName() {
        return getStr(INVENTARIO_NAME);
    }
    
    public static Date getDateFrom(){
        return TMP_DATE_FROM;
    }
    
    public static void setDateFrom(Date date){
        TMP_DATE_FROM = date;
    }
    
    public static Date getDateTo(){
        return TMP_DATE_TO;
    }
    
    public static void setDateTo(Date date){
        TMP_DATE_TO = date;
    }
    
    public static String getSalt(){
        return getStr(SALT);
    }
    
    public static String getEquipo(){
        return getStr(EQUIPO);
    }
    
    public static void setEquipo(String equipo){
        EQUIPO = getStr(equipo)+"_"+dateToString(new Date());
    }
}
