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
    public static String EXP_DATE = "07-08-2018";
    public static String API_URI = "www.sdx.cl";
    
    /* Update */
    public static int ID_UPDATE=0;
    public static String PORT_KEY = "KEYs";
    public static String OFFICE = "null";
    
    
    /* Variables del sistema */
    
    private static String USERNAME;
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

    public static void setLicence(boolean value) {
        LICENCE = value;
    }
    
    public static String apiUriLicence(){
        return API_URI;
    }
    
    public static String urlUriPort(){
        return PORT_KEY;
    }
    
    public static String getOfficeName(){
        return getStr(OFFICE);
    }
    
    public static void setOfficeName(String offineName){
        OFFICE = getStr(offineName);
    }

    public static void setUserName(String userName) {
        USERNAME = getStr(userName);
    }

    public static String getUserName() {
        return getStr(USERNAME);
    }

    public static void setExpDate(String date) {
        EXP_DATE = getStr(date);
    }

    public static void setApiUriLicence(String uri) {
        API_URI = getStr(uri);
    }
    
    public static void setApiUriPort(String port) {
        PORT_KEY = getStr(port);
    }
}
