/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import bd.LcBd;
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
import fn.globalValues.GlobalValuesPrint;
import fn.globalValues.GlobalValuesSaveXls;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesUI;
import fn.globalValues.GlobalValuesXmlFiles;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import sync.entities.Remote;
import view.opanel.MPanel;
import view.opanel.OPanel;

/**
 *s
 * @author sdx
 */
public class GV extends GlobalValuesCursor{
    private static String className="GlobalValues";
    
    
    
    /*  Sincronizacion */
    public static Global GLOBAL_SYNC = new Global();
    public static Local LOCAL_SYNC = new Local();
    public static Remote REMOTE_SYNC = new Remote();
    
    public static Date LAST_UPDATE;
    
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
        try {
            LcBd.obtener();
            LcBd.cerrar();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            initDB();
        }
        SubProcess.isOnline();
        
        loadLastUpdateFromXML();//cargar LAST_UPDATE de fichero xml al iniciar programa
    }
    /*********************BEGIN PORCENTAJE SYNC***************************/
    public static void porcentajeCalcular(int limit){
        GlobalValuesSyncReportStatus.calcularPorcentaje(limit);
    }
    
    public static void porcentajeSubCalcular(int subLimit){
        GlobalValuesSyncReportStatus.calcularSubPorcentaje(subLimit);
    }
    
    public static int porcentaje(){
        return GlobalValuesSyncReportStatus.getPorc();
    }
    
    public static String getReporte(){
        return GlobalValuesSyncReportStatus.getReport();
    }
    
    public static void setReporte(String reporte){
        GlobalValuesSyncReportStatus.setReport(reporte);
    }
    
    public static void porcentajeReset(){
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
    
    public static void isOnline(boolean value){
        GlobalValuesNetwork.setIsOnline(value);
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
     /**************************BEGIN PRINT**********************************/
    public static void printFicha(){
        GlobalValuesPrint.print(getFicha());
    }
    
    public static void printFicha(Ficha ficha){
        GlobalValuesPrint.print(ficha);
    }
     /**************************END PRINT************************************/
    /**************************BEGIN FUNTIONS**********************************/
    public static String dateToString(Date date, String format){
        return GlobalValuesFunctions.dateToString(date,format);
    }
    
    public static int roundPrice(int price) {
        return GlobalValuesFunctions.roundPrice(price);
    }
    
    public static int contChar(char toCompare, String toCount){
        return GlobalValuesFunctions.contChar(toCompare, toCount);
    }
    
    public static String getStr(String arg){
        return GlobalValuesFunctions.getStr(arg);
    }
    
    public static String className(Object type){
        return GlobalValuesFunctions.getClassName(type);
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
    
    public static void setHourToFicha(JSpinner txth1, JSpinner txtm1, JSpinner txth2, JSpinner txtm2) {
        GlobalValuesFunctions.setHourToFicha(txth1,txtm1,txth2,txtm2);
    }
    
    public static boolean isNumeric(String arg){
        return GlobalValuesFunctions.isNumeric(arg);
    }
    
    public static int abonosMonto(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoMontoFromArray(index,abonos);
    }
    
    public static Date abonosDate(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoDateFromArray(index,abonos);
    }
    
    public static String abonosName(int index,Object[][] abonos){
        return GlobalValuesFunctions.abonoNameFromArray(index,abonos);
    }
    
    public static Object[][] abonosListArrayFromFicha(String codFicha){
        return GlobalValuesFunctions.listarAbonos(codFicha);
    }
    /**************************END FUNTIONS**********************************/
    /*****************************BEGIN VARIABLES DEL SISTEMA***************************************/
    public static void username(String userName) {
        GlobalValuesVariables.setUserName(userName);
    }
    
    public static int cboFichasFilter(){
        return GlobalValuesVariables.cboFichasFilter();
    }
    
    public static void setCboFichasFilter(int filter){
        GlobalValuesVariables.setCboFichasFilter(filter);
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
    
    public static void setInventarioLocalFromXml(String inventario){
        GlobalValuesVariables.setInventarioLocal(inventario);
    }
    
    public static String inventarioName(){
        return GlobalValuesVariables.getInventarioName();
    }
    
    public static void setCompanyName(String nombre) {
        GlobalValuesVariables.setCompanyName(nombre);
        saveXMLProperties();
    }
    
    public static void setCompanyNameFromXml(String nombre) {
        GlobalValuesVariables.setCompanyName(nombre);
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
    
    public static String licenceCode(){
        return GlobalValuesVariables.getLicenceCode();
    }
    
    public static void setLicenceCode(String licenceCode){
        GlobalValuesVariables.setLicenceCode(licenceCode);
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
    
    public static String cleanIdParam(String arg){
        return GlobalValuesVariables.cleanIdParam(arg);
    }
    
    public static boolean fichaIdParamIsIdFicha(String arg){
        return GlobalValuesVariables.fichaIdParamIsIdFicha(arg);
    }
    
    public static boolean fichaIdParamIsUser(String arg){
        return GlobalValuesVariables.fichaIdParamIsUser(arg);
    }
    
    public static boolean fichaIdParamIsTableList(String arg){
        return GlobalValuesVariables.fichaIdParamIsTableList(arg);
    }
    
    public static boolean fichaIdParamIsDateList(String arg) {
        return GlobalValuesVariables.fichaIdParamIsDateList(arg);
    }
    
    public static boolean fichaIdParamIsClient(String arg){
        return GlobalValuesVariables.fichaIdParamIsClient(arg);
    }
    
    public static String convertFichaIdParamToUSer(String arg){
        return GlobalValuesVariables.convertFichaIdParamToUSer(arg);
    }
    
    public static String convertFichaIdParamToClient(String arg){
        return GlobalValuesVariables.convertFichaIdParamToClient(arg);
    }
    
    public static String convertFichaIdParamToListAbonos(String arg){
        return GlobalValuesVariables.convertFichaIdParamToListAbonos(arg);
    }
    
    public static String convertFichaIdParamToTableList(String arg){
        return GlobalValuesVariables.convertFichaIdParamToTableList(arg);
    }
    
    public static String convertFichaIdParamToDateList(String arg){
        return GlobalValuesVariables.convertFichaIdParamToDateList(arg);
    }
    
    public static String estadoFicha(int status){
        return GlobalValuesVariables.estadoFicha(status);
    }
    
    public static int estadoFichaDeleted(){
        return GlobalValuesVariables.estadoFichaDeleted();
    }
    
    public static int estadoFichaPending(){
        return GlobalValuesVariables.estadoFichaPending();
    }
    
    public static int estadoFichaPaid(){
        return GlobalValuesVariables.estadoFichaPaid();
    }
    
    public static int estadoFichaDelivered(){
        return GlobalValuesVariables.estadoFichaDelivered();
    }
    
    public static int estadoFichaWarranty(){
        return GlobalValuesVariables.estadoFichaWarranty();
    }
    /*****************************END VARIABLES DEL SISTEMA***************************************/
    /*****************************BEGIN BD***************************************/
    public static void backUpLocalBd(){
        GlobalValuesBD.backUpLocalBd();
    }
    
    public static void sincronizarTodo(){
        GlobalValuesBD.sincronizarTodo();
    }
    
    /**
     * usar funcion sincronizarGetEntitiesList, agregar los tipos de entidades 
     * y enviar como parametro
     * @param entitiesList 
     */
    public static void sincronizarEntitiesList(List<Object> entitiesList){
        GlobalValuesBD.sincronizar(entitiesList);
    }
    
    public static void stopSincronizacion(){
        GlobalValuesBD.setSincronizar(false);
    }
    
    public static void startSincronizacion() {
        GlobalValuesBD.setSincronizar(true);
    }
    
    public static boolean sincronizacionIsStopped() {
        return !GlobalValuesBD.sincronizacion();
    }
    
    public static Connection initDB(){
        return GlobalValuesBD.initDB();
    }
    
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
    
    public static void listarFichasByDate(Date date1, Date date2) {
        GlobalValuesBD.listarFichasByDate(date1,date2);
    }
    
    public static void listarFichasByClient(String rut) {
        GlobalValuesBD.listarFichasByClient(rut);
    }
    
    public static void listarFichasByUser(String idUser) {
        GlobalValuesBD.listarFichasByUser(idUser);
    }
    
    public static List<Object> getFichas() {
        return GlobalValuesBD.getFichas();
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
    
    /**
     * retorna la ficha seleccionada para mostrar los datos
     * @return 
     */
    public static Ficha getOpenFicha(){
        return GlobalValuesEntities.getOpenFicha();
    }
    
    /**
     * Asigna una ficha para dejarla estática y mostrar sus datos en una nueva ventana
     * @param ficha 
     */
    public static void setOpenFicha(Ficha ficha){
        GlobalValuesEntities.setOpenFicha(ficha);
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
    
    public static String getOficinaWeb() {
        return GlobalValuesEntities.getOficinaWeb();
    }
    
    public static String getOficinaAddress() {
        return GlobalValuesEntities.getOficinaAddress();
    }
    
    public static String getOficinaCity() {
        return GlobalValuesEntities.getOficinaCity();
    }
    
    public static String getOficinaMail(){
        return GlobalValuesEntities.getOficinaMail();
    }
    
    public static String getOficinaPhone1(){
        return GlobalValuesEntities.getOficinaPhone1();
    }
    
    public static String getOficinaPhone2(){
        return GlobalValuesEntities.getOficinaPhone2();
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
    public static String directoryFilesPath(){
        return GlobalValuesDirectories.getFilesPath();
    }
    
    public static String directoryLocalPath(){
        return GlobalValuesDirectories.getLocalPath();
    }
    
    public static String directoryReportViewPath(){
        return GlobalValuesDirectories.getReportViewPath();
    }
    
    public static String directoryReportExcelPath(){
        return GlobalValuesDirectories.getReportExcelPath();
    }
    
    /*****************************END DIRECTORY***************************************/
    /*****************************BEGIN XML***************************************/
    private static void saveXMLProperties() {
        GlobalValuesXmlFiles.crearRegistroLocal();
    }
    
    public static void loadLastUpdateFromXML() {
        GlobalValuesXmlFiles.cargarRegistroLocal();
    }
    /*****************************END XML***************************************/
    /*****************************BEGIN XSL***************************************/
    public static void excelAllMails() {
        GlobalValuesSaveXls.saveAllMails();
    }
    
    public static void excelUnDeliveredMails() {
        GlobalValuesSaveXls.saveUnDeliveredMails();
    }
    
    public static void excelDebtMails() {
        GlobalValuesSaveXls.saveDebtMails();
    }
    /*****************************END XSL***************************************/
    /**
     * Actualiza archivo local de propiedades del sistema con los valores estaticos
     */
    

    public static void setLastUpdate(Date date) {
        LAST_UPDATE = date;
        saveXMLProperties();
    }
    
    public static void setLastUpdateFromXml(Date date) {
        LAST_UPDATE = date;
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
}
