/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import newpackage.NoGit;
import sync.Cmp;
import sync.entities.Global;
import sync.entities.Local;
import sync.entities.LocalFicha;
import sync.entities.Remote;
import sync.entities.RemoteFicha;
import view.opanel.OPanel;

/**
 *
 * @author sdx
 */
public class GlobalValues {
    private static String className="GlobalValues";
    /*  Nombres de sistema  */
    public static String PROJECTNAME="DCS Optics";
    public static String VERSION = "v4.0.0";
    public static String EQUIPO="jorge";//el nombre debe concatenarse con la fecha de instalacion
    public static int EQUIPO_ID = 1;
    public static String INVENTARIO = "INV";
    
    /* Bases de datos*/
    public static String BD_URL_REMOTE = NoGit.URL;
    public static String BD_NAME_REMOTE = NoGit.DB;
    public static String BD_USER_REMOTE = NoGit.USER;
    public static String BD_PASS_REMOTE = NoGit.PASS;
    public static String BD_URL_LOCAL = "localhost:1527";
    public static String BD_NAME_LOCAL = "odm4";
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
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
    
    /* Mail */
    public static String MAIL_ADDRES = "sdx.respaldo.bd@gmail.com";
    public static String MAIL_PASS= "qwpzedzqucvpyjzt";
    public static String MAIL_REPORT= "softdirex@gmail.com";
    public static String MAIL_LOG = "";
    
    /* Direcciones de fichero*/
    public static String FILES_PATH = "";
    public static String LOCAL_PATH = "";
    
    /* Variables del sistema */
    public static User USER;
    public static int ID_USER = 0;
    public static Date TMP_DATE_FROM = null;
    public static Date TMP_DATE_TO =null;
    /*  Sincronizacion */
    public static Global GLOBAL_SYNC = new Global();
    public static Local LOCAL_SYNC = new Local();
    public static LocalFicha LOCAL_SYNC_FICHA = new LocalFicha();
    public static Remote REMOTE_SYNC = new Remote();
    public static RemoteFicha REMOTE_SYNC_FICHA = new RemoteFicha();
    public static boolean IS_ONLINE = true;
    public static Date LAST_UPDATE = new Date(213312);
    
    //LISTAS TEMPORALES
    public static ArrayList<User> TMP_LIST_USERS = new ArrayList<User>();
    public static ArrayList<Cristal> TMP_LIST_CRISTAL = new ArrayList<Cristal>();
    public static ArrayList<Descuento> TMP_LIST_DESCUENTO = new ArrayList<Descuento>();
    public static ArrayList<Cliente> TMP_LIST_CLIENTES = new ArrayList<Cliente>();
    public static ArrayList<Doctor> TMP_LIST_DOCTORES = new ArrayList<Doctor>();
    public static ArrayList<Institucion> TMP_LIST_INSTITUCIONES = new ArrayList<Institucion>();
    public static ArrayList<Lente> TMP_LIST_LENTES = new ArrayList<Lente>();
    public static ArrayList<Oficina> TMP_LIST_OFICINAS = new ArrayList<Oficina>();
    public static ArrayList<RegistroBaja> TMP_LIST_REGISTROS_BAJAS = new ArrayList<RegistroBaja>();
    public static ArrayList<TipoPago> TMP_LIST_TIPOS_PAGO = new ArrayList<TipoPago>();
    
    /* Joption Pane del sistema */
    public static OPanel INFOPANEL = new OPanel();
    public static String PANELTITLE ="";
    public static int MSG_STATUS;
    public static String ICON_INFO = "/icons/show_info_50px.png";
    public static String ICON_WARN = "/icons/show_warning_50px.png";
    public static String ICON_ERROR = "/icons/show_error_50px.png";
    
    
    
    
    public static void initValues(){
        Log.setLog(className,Log.getReg());
        SubProcess.isOnline();
        SubProcess.sincronizeAll();
        loadLastUpdateFromXML();//cargar LAST_UPDATE de fichero xml al iniciar programa
        LOCAL_PATH = System.getProperty("user.dir")+File.separator;
        FILES_PATH = LOCAL_PATH+"files"+File.separator;
    }
    
    public static void setInventarioLocal(String inventario){
        INVENTARIO = inventario;
        saveXMLProperties();
    }
    
    public static String getMailSystemName() {
        return MAIL_ADDRES;
    }
    
    public static String getMailSystemPass() {
        return MAIL_PASS;
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

    public static String getEqId() throws UnknownHostException{
        return thisPcAddress()+"/"+thisPcName();
    }
    
    private static String thisPcAddress() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
    
    private static String thisPcName() throws UnknownHostException{
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostName();
    }
    private static void loadLastUpdateFromXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static String getPublicIp() throws IOException{
        return Cmp.getPublicIp();
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
    
    public static String getToName(String param){
        if(param == null || param.isEmpty())
            return "";
        String[] str = param.split(" ");
        String value = null;
        for (String temp : str) {
            value = value + " " + Character.toUpperCase(temp.charAt(0)) + temp.substring(1);
        }
        return value.replaceFirst("null", "").replaceFirst(" ", "");
    }
    
    public static String strToPrice(int monto){
        DecimalFormat formateador = new DecimalFormat("###,###,###");
        return "$ "+formateador.format (monto);
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

    /**
     * Actualiza archivo local de propiedades del sistema con los valores estaticos
     */
    private static void saveXMLProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
