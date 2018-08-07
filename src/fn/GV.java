/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import com.toedter.calendar.JDateChooser;
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
import entities.ficha.Ficha;
import fn.globalValues.GlobalValuesBD;
import fn.globalValues.GlobalValuesCursor;
import fn.globalValues.GlobalValuesDirectories;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesVariables;
import fn.globalValues.GlobalValuesEntities;
import fn.globalValues.GlobalValuesMailProperties;
import fn.globalValues.GlobalValuesNetwork;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesUI;
import fn.globalValues.GlobalValuesXmlFiles;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import sync.entities.Global;
import sync.entities.Local;
import sync.entities.LocalFicha;
import sync.entities.Remote;
import sync.entities.RemoteFicha;
import view.opanel.MPanel;
import view.opanel.OPanel;

/**
 *
 * @author sdx
 */
public class GV extends GlobalValuesCursor{
    private static String className="GlobalValues";
    
    
    
    /*  Sincronizacion */
    public static Global GLOBAL_SYNC = new Global();
    public static Local LOCAL_SYNC = new Local();
    public static LocalFicha LOCAL_SYNC_FICHA = new LocalFicha();
    public static Remote REMOTE_SYNC = new Remote();
    public static RemoteFicha REMOTE_SYNC_FICHA = new RemoteFicha();
    
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
    
    
    public static void initValues(){
        Log.setLog(className,Log.getReg());
        SubProcess.isOnline();
        SubProcess.sincronizeAll();
        loadLastUpdateFromXML();//cargar LAST_UPDATE de fichero xml al iniciar programa
    }
    /*********************BEGIN PORCENTAJE SYNC***************************/
    public static void calcularPorcentaje(int limit){
        GlobalValuesSyncReportStatus.calcularPorcentaje(limit);
    }
    
    public static void calcularPorcentajeTotal(int totalProcesos){
        GlobalValuesSyncReportStatus.calcularPorcentajeTotal(totalProcesos);
    }
    
    public static int porcentaje(){
        return GlobalValuesSyncReportStatus.getPorc();
    }
    
    public static int porcentajeTotal(){
        return GlobalValuesSyncReportStatus.getTotal();
    }
    
    public static void resetPorcentaje(){
        GlobalValuesSyncReportStatus.resetPorc();
    }
    
    public static void resetAllPorcentaje(){
        GlobalValuesSyncReportStatus.resetAll();
    }
    /*********************END PORCENTAJE SYNC***************************/
    /*********************BEGIN UI PROPERTIES****************************/
    public static int msgStatus(){
        return GlobalValuesUI.getMsgStatus();
    }
    
    public static void setMsgStatus(int msgStatus){
        GlobalValuesUI.setMsgStatus(msgStatus);
    }
    
    public static String iconInfo(){
        return GlobalValuesUI.iconInfo();
    }
    
    public static String iconWarn(){
        return GlobalValuesUI.iconWarn();
    }
    
    public static String iconError(){
        return GlobalValuesUI.iconError();
    }
    
    public static OPanel opanel(){
        return GlobalValuesUI.opanel();
    }
    
    public static MPanel mpanel(){
        return GlobalValuesUI.mpanel();
    }
    /*********************ENG UI PROPERTIES****************************/
    /*********************BEGIN CHECK ONLINE*****************************/
    public static boolean isOnline(){
        return GlobalValuesNetwork.isOnline();
    }
    
    public static void chekOnline(){
        GlobalValuesNetwork.checkIfOnline();
    }
    /*********************END CHECK ONLINE*****************************/
    /*********************BEGIN MAIL*****************************/
    public static String getMailSystemName() {
        return GlobalValuesMailProperties.getMailSystemName();
    }
    
    public static String getMailSystemPass() {
        return GlobalValuesMailProperties.getMailSystemPass();
    }
    
    public static void setMailLog(String className, String registry){
        GlobalValuesMailProperties.setMailLog(className, registry);
    }
    
    public static String mailLog(){
        return GlobalValuesMailProperties.getMailLog();
    }
    
    public static String mailReport(){
        return GlobalValuesMailProperties.getMailReport();
    }
    /*********************END MAIL*****************************/
    /**************************BEGIN FUNTIONS**********************************/
    public static String dateToString(Date date, String format){
        return GlobalValuesFunctions.dateToString(date,format);
    }
    
    public static int contChar(char toCompare, String toCount){
        return GlobalValuesFunctions.contChar(toCompare, toCount);
    }
    
    public static String getStr(String arg){
        return GlobalValuesFunctions.getStr(arg);
    }
    public static String getToName(String param){
        return GlobalValuesFunctions.getToName(param);
    }
    
    public static void emptyTable(JComboBox cbo, JTextField txt, String registry){
        GlobalValuesFunctions.emptyTable(cbo, txt, registry);
    }
    
    public static String strToPrice(int monto){
        return GlobalValuesFunctions.strToPrice(monto);
    }
    
    public static int strToNumber(String arg){
        return GlobalValuesFunctions.strToNumber(arg);
    }
    
    public static String toHour(int hora, int min){
        return GlobalValuesFunctions.toStrHour(hora,min);
    }

    public static boolean strCompare(String str1, String str2) {
        return GlobalValuesFunctions.strCompare(str1, str2);
    }
    public static String formatoHora(int hora, int min) {
        return GlobalValuesFunctions.formatoHora(hora, min);
    }
    
    public static boolean containIntegrs(String arg){
        return GlobalValuesFunctions.containIntegrs(arg);
    }
    
    public static int diasRestatntes(String strExpDate){
        try {
            return GlobalValuesFunctions.diasRestantes(strExpDate);
        } catch (ParseException ex) {
            Logger.getLogger(GV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static Date strToDate(String strFecha){
        return GlobalValuesFunctions.strToDate(strFecha);
    }
    
    /**
     * retorna un booleano indicando si la fecha entregada por parametro 
     * corresponde al dia de hoy, compara solo el día, mes y año
     * @param date
     * @return 
     */
    public static boolean isCurrentDate(Date date) {
        return GlobalValuesFunctions.isCurrentDate(date);
    }
    
    public static int obtenerDescuentoFicha(Descuento descuento, int total) {
        return GlobalValuesFunctions.obtenerDescuentoFicha(descuento,total);
    }
    
    public static void compileJCalendar(JDateChooser jDate) {
        GlobalValuesFunctions.compileJCalendar(jDate);
    }
    /**************************END FUNTIONS**********************************/
    /*****************************BEGIN VARIABLES DEL SISTEMA***************************************/
    public static void username(String userName) {
        GlobalValuesVariables.setUserName(userName);
    }
    
    public static String username() {
        return GlobalValuesVariables.getUserName();
    }
    
    public static String salt(){
        return GlobalValuesVariables.getSalt();
    }
    public static Date dateFrom(){
        return GlobalValuesVariables.getDateFrom();
    }
    
    public static Date dateTo(){
        return GlobalValuesVariables.getDateTo();
    }
    
    public static void setDateFrom(Date date){
        GlobalValuesVariables.setDateFrom(date);
    }
    
    public static void setDateTo(Date date){
        GlobalValuesVariables.setDateTo(date);
    }
    
    public static void setInventarioLocal(String inventario){
        GlobalValuesVariables.setInventarioLocal(inventario);
        saveXMLProperties();
    }
    
    public static String inventarioName(){
        return GlobalValuesVariables.getInventarioName();
    }
    
    public static void setCompanyName(String nombre) {
        GlobalValuesVariables.setCompanyName(nombre);
        saveXMLProperties();
    }
    
    public static String companyName(){
        return GlobalValuesVariables.getCompanyName();
    }
    
    public static String projectName(){
        return GlobalValuesVariables.getProjectName();
    }
    
    public static String version(){
        return GlobalValuesVariables.getVersion();
    }
    
    public static boolean licence(){
        return GlobalValuesVariables.getLicence();
    }
    
    public static void licence(boolean value){
        GlobalValuesVariables.setLicence(value);
    }
    
    public static String expDate(){
        return GlobalValuesVariables.getExpDate();
    }
    
    public static void expDate(String date){
        GlobalValuesVariables.setExpDate(date);
    }
    
    public static String equipo(){
        return GlobalValuesVariables.getEquipo();
    }
    
    public static void setCurrentEquipo(String name){
        GlobalValuesVariables.setCurrentEquipo(name);
    }
    
    public static void setLicence(boolean value) {
        GlobalValuesVariables.setLicence(value);
    }
    
    public static String uri(){
        return GlobalValuesVariables.apiUriLicence();
    }
    
    public static void setUri(String uri){
        GlobalValuesVariables.setApiUriLicence(uri);
    }
    
    public static String port(){
        return GlobalValuesVariables.urlUriPort();
    }
    
    public static void setPort(String port){
        GlobalValuesVariables.setApiUriPort(port);
    }
    
    
    /*****************************END VARIABLES DEL SISTEMA***************************************/
    /*****************************BEGIN BD***************************************/
    public static String getLocalBdUser() {
        return GlobalValuesBD.getLocalBdUser();
    }

    public static String getLocalBdPass() {
        return GlobalValuesBD.getLocalBdPass();
    }

    public static String getLocalBdUrl() {
        return GlobalValuesBD.getLocalBdUrl();
    }

    public static String getLocalBdName() {
        return GlobalValuesBD.getLocalBdName();
    }
    
    public static String getRemoteBdUser() {
        return GlobalValuesBD.getRemoteBdUser();
    }

    public static String getRemoteBdPass() {
        return GlobalValuesBD.getRemoteBdPass();
    }

    public static String getRemoteBdUrl() {
        return GlobalValuesBD.getRemoteBdUrl();
    }

    public static String getRemoteBdName() {
        return GlobalValuesBD.getRemoteBdName();
    }
    
    /*****************************END BD***************************************/
    
    //******************************** BEGIN CURSOR ***************************************************
    public static void cursorDF(){
        GlobalValuesCursor.cursorDF();
    }
    
    public static void cursorWAIT(){
        GlobalValuesCursor.cursorWAIT();
    }
    
    public static void cursorDF(JPanel panel){
        GlobalValuesCursor.cursorDF(panel);
    }
    
    public static void cursorWAIT(JPanel panel){
        GlobalValuesCursor.cursorWAIT(panel);
    }
    
    //******************************** END CURSOR ***************************************************/
    /********************************** BEGIN ENTITIES **************************************/
    public static void clearFicha(){
        GlobalValuesEntities.clearFicha();
    }
    
    public static Ficha getFicha(){
        return GlobalValuesEntities.getFicha();
    }
    
    public static void setFicha(Ficha ficha){
        GlobalValuesEntities.setFicha(ficha);
    }
    public static User user(){
        return GlobalValuesEntities.getSessionUser();
    }
    
    public static void setUser(User user){
        GlobalValuesEntities.setSessionUser(user);
    }
    
    public static void setOficina(Oficina oficina){
        GlobalValuesEntities.setOficina(oficina);
        saveXMLProperties();
    }
    
    public static void setOficina(String nombre){
        if(GlobalValuesEntities.setOficina(nombre)){
            saveXMLProperties();
        }
    }

    public static String getLblNombreOficina() {
        return GlobalValuesEntities.getLblNombreOficina();
    }
    
    public static String getNombreOficina() {
        return GlobalValuesEntities.getNombreOficina();
    }
    
    public static boolean tipoUserSuperAdmin(){
        return GlobalValuesEntities.tipoUserSuperAdmin();
    }
    
    public static boolean tipoUserAdmin(){
        return GlobalValuesEntities.tipoUserAdmin();
    }
    
    public static boolean tipoUserIventario(){
        return GlobalValuesEntities.tipoUserIventario();
    }
    /********************************** END ENTITIES **************************************/
    /*****************************BEGIN DIRECTORY***************************************/
    public static String filesPath(){
        return GlobalValuesDirectories.getFilesPath();
    }
    
    public static String localPath(){
        return GlobalValuesDirectories.getLocalPath();
    }
    
    /*****************************END DIRECTORY***************************************/
    /*****************************BEGIN XML***************************************/
    private static void saveXMLProperties() {
        GlobalValuesXmlFiles.crearRegistroLocal();
    }
    
    private static void loadLastUpdateFromXML() {
        GlobalValuesXmlFiles.cargarRegistroLocal();
    }
    /*****************************END XML***************************************/
    /**
     * Actualiza archivo local de propiedades del sistema con los valores estaticos
     */
    

    public static void setLastUpdate(Date date) {
        LAST_UPDATE = date;
        saveXMLProperties();
    }
    
    public static Date getLastUpdate(){
        return LAST_UPDATE;
    }

    public static String mailValidate(String email) {
        email = getStr(email).toLowerCase();
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
 
        Matcher mather = pattern.matcher(email);
        if(mather.find()){
            return email;
        }
        return "";
    }

    public static void setHourToFicha(JSpinner txth1, JSpinner txtm1, JSpinner txth2, JSpinner txtm2) {
        GlobalValuesFunctions.setHourToFicha(txth1,txtm1,txth2,txtm2);
    }

    public static String idCurrentFicha() {
        return "idFicha";
    }
}