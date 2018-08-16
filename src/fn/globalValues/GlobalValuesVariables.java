/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import dao.Dao;
import entities.Equipo;
import fn.GV;
import static fn.GV.dateToString;
import static fn.GV.getStr;
import static fn.GV.getToName;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesVariables {
    
    /*  Nombres de sistema  */
    private static String PROJECTNAME="DCS Optics";
    private static String VERSION = "v4.0.0";
    private static String EQUIPO;//el nombre debe concatenarse con la fecha de instalacion
    private static int EQUIPO_ID = 1;
    private static String INVENTARIO_NAME;
    
    /* Seguridad */
    private static String SALT = "optidataodm4softdirex";
    private static String PASS;
    
    /* LICENCIA */
    private static String COMPANY_NAME;
    private static boolean LICENCE = false;
    private static String LICENCE_CODE = null;
    private static String EXP_DATE;
    private static String API_URI;
    
    /* Update */
    private static int ID_UPDATE=0;
    private static String PORT_KEY;
    
    
    /* Variables del sistema */
    
    private static String USERNAME;
    private static int ID_USER = 0;
    private static Date TMP_DATE_FROM = null;
    private static Date TMP_DATE_TO =null;
    private static String ID_PARAM_IS_USER = "USER/";
    private static String ID_PARAM_IS_CLIENT = "CLIENT/";
    
    private static final int DELETED = 0;
    private static final int PENDING = 1;
    private static final int PAID = 2;
    private static final int DELIVERED = 3;
    private static final int WARRANTY = 4;
    
    public static void setInventarioLocal(String inventario){
        INVENTARIO_NAME = getStr(inventario);
    }
    
    public static void setCompanyName(String nombre) {
        COMPANY_NAME = getToName(nombre);
    }
    
    public static String getCompanyName(){
        String value = (getStr(COMPANY_NAME).isEmpty())?"[Empresa no ingresada]":getStr(COMPANY_NAME);
        return value;
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
    
    public static String getLicenceCode(){
        return GV.getStr(LICENCE_CODE);
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
        return getToName(EQUIPO);
    }
    
    public static void setCurrentEquipo(String equipo){
        try {
            EQUIPO = getToName(equipo);
            if(LICENCE_CODE != null){
                Dao load = new Dao();
                Equipo eqData = new Equipo(0, EQUIPO, LICENCE_CODE, 1, null, 0);
                if(load.get(EQUIPO, 0, eqData) == null){
                    load.add(eqData);
                }
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GlobalValuesVariables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setEquipo(String equipo){
        EQUIPO = getStr(equipo)+"_"+dateToString(new Date(),"yyyymmdd");
    }

    public static void setLicence(boolean value) {
        LICENCE = value;
    }
    
    public static void setLicenceCode(String licenceCode){
        try {
            LICENCE_CODE = getStr(licenceCode);
            if(EQUIPO != null){
                Dao load = new Dao();
                Equipo eqData = new Equipo(0, EQUIPO, LICENCE_CODE, 1, null, 0);
                if(load.get(EQUIPO, 0, eqData) == null){
                    load.add(eqData);
                }
            }
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GlobalValuesVariables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String apiUriLicence(){
        return API_URI;
    }
    
    public static String urlUriPort(){
        return PORT_KEY;
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
    
    public static boolean fichaIdParamIsIdFicha(String arg){
        return (!fichaIdParamIsClient(arg) && !fichaIdParamIsUser(arg)) ? true:false;
    }
    
    public static boolean fichaIdParamIsUser(String arg){
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
    
    public static String cleanIdParam(String arg){
        return (GV.getStr(arg).replaceAll(ID_PARAM_IS_USER, "").replaceAll(ID_PARAM_IS_CLIENT, "")).trim();
    }

    public static String estadoFicha(int status) {
        String value = "---";
        switch(status){
            case DELETED:
                value = "Eliminada";
                break;
            case PENDING:
                value = "Pendiente";
                break;
            case PAID:
                value = "Pagada";
                break;
            case WARRANTY:
                value = "Garantía";
                break;
            case DELIVERED:
                value = "Entregada";
                break;
            default:
                value = "Indefinido";
                break;
        }
        return value;
    }
}
