/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Cristal;
import entities.Lente;
import entities.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import sync.entities.Global;
import sync.entities.Local;
import sync.entities.Remote;

/**
 *
 * @author sdx
 */
public class GlobalValues {
    private static String className="GlobalValues";
    /*  Nombres de sistema  */
    public static String PROJECTNAME="";
    public static String VERSION = "4.0.0";
    
    /* Bases de datos*/
    public static String BD_URL_REMOTE = "localhost";
    public static String BD_NAME_REMOTE = "odmbd";
    public static String BD_USER_REMOTE = "root";
    public static String BD_PASS_REMOTE = "20075321818";
    public static String BD_URL_LOCAL = "localhost:1527";
    public static String BD_NAME_LOCAL = "odm4";
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
    /* Seguridad */
    public static String SALT = "optidataodm4softdirex";
    public static String PASS;
    
    /* LICENCIA */
    public static String COMPANY_NAME;
    public static boolean LICENCE = true;
    public static String EXP_DATE = "00-00-0000";
    public static String API_URI;
    public static String LOCAL_ID;
    
    /* Update */
    public static int ID_UPDATE=0;
    public static String URL_UPDATE;
    
    /* Mail */
    public static String MAIL_ADDRES = "sdx.respaldo.bd@gmail.com";
    public static String MAIL_PASS= "qwpzedzqucvpyjzt";
    public static String MAIL_REPORT= "softdirex@gmail.com";
    public static String MAIL_LOG = "";
    
    /* Direcciones de fichero*/
    public static String FILES_PATH = "";
    public static String LOCAL_PATH = "";
    
    /* Variables del sistema */
    public static String USERNAME = "";
    public static int ID_USER = 0;
    public static String TMP_RUT_DOCTOR;
    public static int TMP_ID_INSTITUCION;
    public static int TMP_ID_DESCUENTO;
    public static Date TMP_DATE_FROM = null;
    public static Date TMP_DATE_TO =null;
    /*  Sincronizacion */
    public static Global GLOBAL_SYNC = new Global();
    public static Local LOCAL_SYNC = new Local();
    public static Remote REMOTE_SYNC = new Remote();
    public static boolean IS_ONLINE = false;
    
    //LISTAS TEMPORALES
    public static ArrayList<User> TMP_LIST_USERS = new ArrayList<User>();
    public static ArrayList<Cristal> TMP_LIST_CRISTAL = new ArrayList<Cristal>();
    public static ArrayList<Lente> TMP_LIST_LENTES = new ArrayList<Lente>();
    
    /* Joption Pane del sistema */
    public static String PANELTITLE ="";
    public static int MSG_STATUS;
    public static String ICON_INFO = "/icons/show_info_50px.png";
    public static String ICON_WARN = "/icons/show_warning_50px.png";
    public static String ICON_ERROR = "/icons/show_error_50px.png";
    
    
    
    
    public static void initValues(){
        Log.setLog(className,Log.getReg());
        SubProcess.isOnline();
        LOCAL_PATH = System.getProperty("user.dir")+File.separator;
        FILES_PATH = LOCAL_PATH+"files"+File.separator;
    }
    
    
    public static String getLocalBdUser() {
        return BD_USER_LOCAL;
    }

    public static String getLocalBdPass() {
        return BD_PASS_LOCAL;
    }

    public static String getLocalBdUrl() {
        return BD_URL_LOCAL;
    }

    public static String getLocalBdName() {
        return BD_NAME_LOCAL;
    }
    
    public static String getRemoteBdUser() {
        return BD_USER_REMOTE;
    }

    public static String getRemoteBdPass() {
        return BD_PASS_REMOTE;
    }

    public static String getRemoteBdUrl() {
        return BD_URL_REMOTE;
    }

    public static String getRemoteBdName() {
        return BD_NAME_REMOTE;
    }
    
    public static boolean isOnline(){
        return IS_ONLINE;
    }
    
    

}
