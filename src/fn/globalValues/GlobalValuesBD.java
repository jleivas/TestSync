/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import bd.LcBd;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.CuotasConvenio;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.InternMail;
import entities.InternStock;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import fn.OptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;
import newpackage.NoGit;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesBD {
    /* Bases de datos*/
    public static String BD_URL_REMOTE = NoGit.URL;
    public static String BD_NAME_REMOTE = NoGit.DB;
    public static String BD_USER_REMOTE = NoGit.USER;
    public static String BD_PASS_REMOTE = NoGit.PASS;
    public static String BD_URL_LOCAL = "localhost:1527";
    public static String BD_NAME_LOCAL = "odm4";
    public static String BD_USER_LOCAL = "odm4";
    public static String BD_PASS_LOCAL = "odm4";
    
    private static int arm = 1;
    private static int cli = 2;
    private static int cnv = 3;
    private static int cri = 4;
    private static int cc = 5;
    private static int des = 6;
    private static int dsp = 7;
    private static int doc = 8;
    private static int eq = 9;
    private static int fch = 10;
    private static int hp = 11;
    private static int ins = 12;
    private static int ist = 13;
    private static int inv = 14;
    private static int len = 15;
    private static int msg = 16;
    private static int of = 17;
    private static int rb = 18;
    private static int tp = 19;
    private static int usu = 20;
    
    
    private static String ARMAZON = "ARM_ID VARCHAR(25) not null primary key," +
" ARM_TIPO INTEGER," +
" ARM_MARCA VARCHAR(45)," +
" ARM_CRISTAL VARCHAR(45)," +
" ARM_ADD VARCHAR(45)," +
" ARM_OD_A VARCHAR(45)," +
" ARM_OD_ESF VARCHAR(45)," +
" ARM_OD_CIL VARCHAR(45)," +
" ARM_OI_A VARCHAR(45)," +
" ARM_OI_ESF VARCHAR(45)," +
" ARM_OI_CIL VARCHAR(45)," +
" ARM_DP INTEGER," +
" ARM_ENDURECIDO INTEGER," +
" ARM_CAPA INTEGER," +
" ARM_PLUS_MAX INTEGER," +
" FICHA_FCH_ID VARCHAR(25)," +
" ARM_ESTADO INTEGER," +
" ARM_LAST_UPDATE DATE," +
" ARM_LAST_HOUR INTEGER";
    private static String CLIENTE = "CLI_RUT VARCHAR(25) not null primary key," +
" CLI_NOMBRE VARCHAR(45)," +
" CLI_TELEFONO1 VARCHAR(25)," +
" CLI_TELEFONO2 VARCHAR(25)," +
" CLI_EMAIL VARCHAR(45)," +
" CLI_DIRECCION VARCHAR(45)," +
" CLI_COMUNA VARCHAR(45)," +
" CLI_CIUDAD VARCHAR(45)," +
" CLI_SEXO INTEGER," +
" CLI_NACIMIENTO DATE," +
" CLI_ESTADO INTEGER," +
" CLI_LAST_UPDATE DATE," +
" CLI_LAST_HOUR INTEGER";
    private static String CONVENIO = "CNV_ID INTEGER not null primary key," +
" CNV_NOMBRE VARCHAR(45)," +
" CNV_FECHA_INICIO DATE," +
" CNV_FECHA_FIN DATE," +
" CNV_CUOTAS INTEGER," +
" CNV_MONTO_MAXIMO INTEGER," +
" CNV_MONTO_PP INTEGER," +
" CNV_MAXIMO_CLIENTES INTEGER," +
" DESCUENTO_DES_ID INTEGER," +
" INSTITUCION_INS_ID INTEGER," +
" CNV_ESTADO INTEGER," +
" CNV_LAST_UPDATE DATE," +
" CNV_LAST_HOUR INTEGER";
    private static String CRISTAL = "CRI_ID INTEGER not null primary key," +
" CRI_NOMBRE VARCHAR(45)," +
" CRI_PRECIO INTEGER," +
" CRI_ESTADO INTEGER," +
" CRI_LAST_UPDATE DATE," +
" CRI_LAST_HOUR INTEGER";
    private static String CUOTAS_CONVENIO = "CC_ID INTEGER not null primary key," +
" CC_FECHA DATE," +
" CC_MONTO INTEGER," +
" CC_ESTADO INTEGER," +
" CC_LAST_UPDATE DATE," +
" CC_LAST_HOUR INTEGER";
    private static String DESCUENTO = "DES_ID INTEGER not null primary key," +
" DES_NOMBRE VARCHAR(45)," +
" DES_DESCRIPCION LONG VARCHAR," +
" DES_PORC INTEGER," +
" DES_MONTO INTEGER," +
" DES_ESTADO INTEGER," +
" DES_LAST_UPDATE DATE," +
" DES_LAST_HOUR INTEGER";
    private static String DESPACHO = "DSP_ID VARCHAR(25) not null primary key," +
" DSP_RUT VARCHAR(25)," +
" DSP_NOMBRE VARCHAR(45)," +
" DSP_FECHA DATE," +
" FICHA_FCH_ID VARCHAR(25)," +
" DSP_ESTADO INTEGER," +
" DSP_LAST_UPDATE DATE," +
" DSP_LAST_HOUR INTEGER";
    private static String DOCTOR = "DOC_RUT VARCHAR(25)," +
" DOC_NOMBRE VARCHAR(45)," +
" DOC_TELEFONO VARCHAR(25)," +
" DOC_MAIL VARCHAR(45)," +
" DOC_ESTADO INTEGER," +
" DOC_LAST_UPDATE DATE," +
" DOC_LAST_HOUR INTEGER";
    private static String EQUIPO = "EQ_ID INTEGER not null primary key," +
" EQ_NOMBRE VARCHAR(45)," +
" EQ_LICENCIA VARCHAR(45)," +
" EQ_ESTADO INTEGER," +
" EQ_LAST_UPDATE DATE," +
" EQ_LAST_HOUR INTEGER";
    private static String FICHA = "FCH_ID VARCHAR(25) not null primary key," +
" FCH_FECHA DATE," +
" FCH_FECHA_ENTREGA DATE," +
" FCH_LUGAR_ENTREGA VARCHAR(45)," +
" FCH_HORA_ENTREGA VARCHAR(15)," +
" FCH_OBS LONG VARCHAR," +
" FCH_VALOR_TOTAL INTEGER," +
" FCH_DESCUENTO INTEGER," +
" FCH_SALDO INTEGER," +
" CLIENTE_CLI_RUT VARCHAR(25)," +
" DOCTOR_DOC_RUT VARCHAR(25)," +
" INSTITUCION_INS_ID INTEGER," +
" DESPACHO_DSP_ID VARCHAR(25)," +
" USUARIO_US_ID INTEGER,"+
" CONVENIO_CNV_ID INTEGER," +
" FCH_ESTADO INTEGER," +
" FCH_LAST_UPDATE DATE," +
" FCH_LAST_HOUR INTEGER";
    private static String HISTORIAL_PAGO = "HP_ID VARCHAR(25)," +
" HP_FECHA DATE," +
" HP_ABONO INTEGER," +
" TIPO_PAGO_TP_ID INTEGER," +
" FICHA_FCH_ID VARCHAR(25)," +
" HP_ESTADO INTEGER," +
" HP_LAST_UPDATE DATE," +
" HP_LAST_HOUR INTEGER";
    private static String INSTITUCION = "INS_ID INTEGER not null primary key," +
" INS_NOMBRE VARCHAR(45)," +
" INS_TELEFONO VARCHAR(25)," +
" INS_MAIL VARCHAR(45)," +
" INS_WEB VARCHAR(45)," +
" INS_DIRECCION VARCHAR(45)," +
" INS_COMUNA VARCHAR(45)," +
" INS_CIUDAD VARCHAR(45)," +
" INS_ESTADO INTEGER," +
" INS_LAST_UPDATE DATE," +
" INS_LAST_HOUR INTEGER";
    private static String INTERN_STOCK = "ID INTEGER not null primary key," +
" ID_LENTE VARCHAR(25) not null," +
" STOCK INTEGER," +
" ESTADO INTEGER";
    private static String INVENTARIO = "INV_ID INTEGER not null primary key," +
" INV_NOMBRE VARCHAR(45)," +
" INV_DESCRIPCION LONG VARCHAR," +
" INV_ESTADO INTEGER," +
" INV_LAST_UPDATE DATE," +
" INV_LAST_HOUR INTEGER";
    private static String LENTE = "LEN_ID VARCHAR(25) not null primary key," +
" LEN_COLOR VARCHAR(25)," +
" LEN_TIPO VARCHAR(45)," +
" LEN_MARCA VARCHAR(45)," +
" LEN_MATERIAL VARCHAR(45)," +
" LEN_FLEX INTEGER," +
" LEN_CLASIFICACION INTEGER," +
" LEN_DESCRIPCION LONG VARCHAR," +
" LEN_PRECIO_REF INTEGER," +
" LEN_PRECIO_ACT INTEGER," +
" LEN_STOCK INTEGER," +
" LEN_STOCK_MIN INTEGER," +
" INVENTARIO_INV_ID VARCHAR(25)," +
" LEN_ESTADO INTEGER," +
" LEN_LAST_UPDATE DATE," +
" LEN_LAST_HOUR INTEGER";
   private static String MESSAGE = "MSG_ID INTEGER not null primary key," +
" US_ID_REMITENTE INTEGER," +
" US_ID_DESTINATARIO INTEGER," +
" MSG_ASUNTO VARCHAR(45)," +
" MSG_CONTENT LONG VARCHAR," +
" MSG_FECHA DATE," +
" MSG_HORA VARCHAR(25)," +
" MSG_ESTADO INTEGER," +
" MSG_LAST_UPDATE DATE," +
" MSG_LAST_HOUR INTEGER";
   private static String OFICINA = "OF_ID INTEGER not null primary key," +
" OF_NOMBRE VARCHAR(45)," +
" OF_DIRECCION VARCHAR(45)," +
" OF_CIUDAD VARCHAR(45)," +
" OF_TELEFONO1 VARCHAR(25)," +
" OF_TELEFONO2 VARCHAR(25)," +
" OF_EMAIL VARCHAR(45)," +
" OF_WEB VARCHAR(100)," +
" OF_ESTADO INTEGER," +
" OF_LAST_UPDATE DATE," +
" OF_LAST_HOUR INTEGER";
    private static String REGISTRO_BAJAS = "RB_ID VARCHAR(25) not null primary key," +
" RB_FECHA DATE," +
" LENTE_LEN_ID VARCHAR(25)," +
" RB_CANTIDAD INTEGER," +
" RB_OBS LONG VARCHAR," +
" RB_ESTADO INTEGER," +
" RB_LAST_UPDATE DATE," +
" RB_LAST_HOUR INTEGER";
    private static String TIPO_PAGO = "TP_ID INTEGER not null primary key," +
" TP_NOMBRE VARCHAR(45)," +
" TP_ESTADO INTEGER," +
" TP_LAST_UPDATE DATE," +
" TP_LAST_HOUR INTEGER";
    private static String USUARIO = "US_ID INTEGER not null primary key," +
" US_NOMBRE VARCHAR(45)," +
" US_USERNAME VARCHAR(45)," +
" US_EMAIL VARCHAR(45)," +
" US_PASS VARCHAR(100)," +
" US_TIPO INTEGER," +
" US_ESTADO INTEGER," +
" US_LAST_UPDATE DATE," +
" US_LAST_HOUR INTEGER";
    private static String COL_ARMAZON = "ARM_ID," +
" ARM_TIPO," +
" ARM_MARCA," +
" ARM_CRISTAL," +
" ARM_ADD," +
" ARM_OD_A," +
" ARM_OD_ESF," +
" ARM_OD_CIL," +
" ARM_OI_A," +
" ARM_OI_ESF," +
" ARM_OI_CIL," +
" ARM_DP," +
" ARM_ENDURECIDO," +
" ARM_CAPA," +
" ARM_PLUS_MAX," +
" FICHA_FCH_ID," +
" ARM_ESTADO," +
" ARM_LAST_UPDATE," +
" ARM_LAST_HOUR";
    private static String COL_CLIENTE = "CLI_RUT," +
" CLI_NOMBRE," +
" CLI_TELEFONO1," +
" CLI_TELEFONO2," +
" CLI_EMAIL," +
" CLI_DIRECCION," +
" CLI_COMUNA," +
" CLI_CIUDAD," +
" CLI_SEXO," +
" CLI_NACIMIENTO," +
" CLI_ESTADO," +
" CLI_LAST_UPDATE," +
" CLI_LAST_HOUR";
    private static String COL_CONVENIO = "CNV_ID," +
" CNV_NOMBRE," +
" CNV_FECHA_INICIO," +
" CNV_FECHA_FIN," +
" CNV_CUOTAS," +
" CNV_MONTO_MAXIMO," +
" CNV_MONTO_PP," +
" CNV_MAXIMO_CLIENTES," +
" DESCUENTO_DES_ID," +
" INSTITUCION_INS_ID," +
" CNV_ESTADO," +
" CNV_LAST_UPDATE," +
" CNV_LAST_HOUR";
    private static String COL_CRISTAL = "CRI_ID," +
" CRI_NOMBRE," +
" CRI_PRECIO," +
" CRI_ESTADO," +
" CRI_LAST_UPDATE," +
" CRI_LAST_HOUR";
    private static String COL_CUOTAS_CONVENIO = "CC_ID," +
" CC_FECHA," +
" CC_MONTO," +
" CC_ESTADO," +
" CC_LAST_UPDATE," +
" CC_LAST_HOUR";
    private static String COL_DESCUENTO = "DES_ID," +
" DES_NOMBRE," +
" DES_DESCRIPCION," +
" DES_PORC," +
" DES_MONTO," +
" DES_ESTADO," +
" DES_LAST_UPDATE," +
" DES_LAST_HOUR";
    private static String COL_DESPACHO = "DSP_ID," +
" DSP_RUT," +
" DSP_NOMBRE," +
" DSP_FECHA," +
" FICHA_FCH_ID," +
" DSP_ESTADO," +
" DSP_LAST_UPDATE," +
" DSP_LAST_HOUR";
    private static String COL_DOCTOR = "DOC_RUT," +
" DOC_NOMBRE," +
" DOC_TELEFONO," +
" DOC_MAIL," +
" DOC_ESTADO," +
" DOC_LAST_UPDATE," +
" DOC_LAST_HOUR";
    private static String COL_EQUIPO = "EQ_ID," +
" EQ_NOMBRE," +
" EQ_LICENCIA," +
" EQ_ESTADO," +
" EQ_LAST_UPDATE," +
" EQ_LAST_HOUR";
    private static String COL_FICHA = "FCH_ID," +
" FCH_FECHA," +
" FCH_FECHA_ENTREGA," +
" FCH_LUGAR_ENTREGA," +
" FCH_HORA_ENTREGA," +
" FCH_OBS," +
" FCH_VALOR_TOTAL," +
" FCH_DESCUENTO," +
" FCH_SALDO," +
" CLIENTE_CLI_RUT," +
" DOCTOR_DOC_RUT," +
" INSTITUCION_INS_ID," +
" DESPACHO_DSP_ID," +
" USUARIO_US_ID," +
" CONVENIO_CNV_ID,"+
" FCH_ESTADO," +
" FCH_LAST_UPDATE," +
" FCH_LAST_HOUR";
    private static String COL_HISTORIAL_PAGO = "HP_ID," +
" HP_FECHA," +
" HP_ABONO," +
" TIPO_PAGO_TP_ID," +
" FICHA_FCH_ID," +
" HP_ESTADO," +
" HP_LAST_UPDATE," +
" HP_LAST_HOUR";
    private static String COL_INSTITUCION = "INS_ID," +
" INS_NOMBRE," +
" INS_TELEFONO," +
" INS_MAIL," +
" INS_WEB," +
" INS_DIRECCION," +
" INS_COMUNA," +
" INS_CIUDAD," +
" INS_ESTADO," +
" INS_LAST_UPDATE," +
" INS_LAST_HOUR";
    private static String COL_INTERN_STOCK = "ID," +
" ID_LENTE," +
" STOCK," +
" ESTADO";
    private static String COL_INVENTARIO = "INV_ID," +
" INV_NOMBRE," +
" INV_DESCRIPCION," +
" INV_ESTADO," +
" INV_LAST_UPDATE," +
" INV_LAST_HOUR";
    private static String COL_LENTE = "LEN_ID," +
" LEN_COLOR," +
" LEN_TIPO," +
" LEN_MARCA," +
" LEN_MATERIAL," +
" LEN_FLEX," +
" LEN_CLASIFICACION," +
" LEN_DESCRIPCION," +
" LEN_PRECIO_REF," +
" LEN_PRECIO_ACT," +
" LEN_STOCK," +
" LEN_STOCK_MIN," +
" INVENTARIO_INV_ID," +
" LEN_ESTADO," +
" LEN_LAST_UPDATE," +
" LEN_LAST_HOUR";
   private static String COL_MESSAGE = "MSG_ID," +
" US_ID_REMITENTE," +
" US_ID_DESTINATARIO," +
" MSG_ASUNTO," +
" MSG_CONTENT," +
" MSG_FECHA," +
" MSG_HORA," +
" MSG_ESTADO," +
" MSG_LAST_UPDATE," +
" MSG_LAST_HOUR";
   private static String COL_OFICINA = "OF_ID," +
" OF_NOMBRE," +
" OF_DIRECCION," +
" OF_CIUDAD," +
" OF_TELEFONO1," +
" OF_TELEFONO2," +
" OF_EMAIL," +
" OF_WEB," +
" OF_ESTADO," +
" OF_LAST_UPDATE," +
" OF_LAST_HOUR";
    private static String COL_REGISTRO_BAJAS = "RB_ID," +
" RB_FECHA," +
" LENTE_LEN_ID," +
" RB_CANTIDAD," +
" RB_OBS," +
" RB_ESTADO," +
" RB_LAST_UPDATE," +
" RB_LAST_HOUR";
    private static String COL_TIPO_PAGO = "TP_ID," +
" TP_NOMBRE," +
" TP_ESTADO," +
" TP_LAST_UPDATE," +
" TP_LAST_HOUR";
    private static String COL_USUARIO = "US_ID," +
" US_NOMBRE," +
" US_USERNAME," +
" US_EMAIL," +
" US_PASS," +
" US_TIPO," +
" US_ESTADO," +
" US_LAST_UPDATE," +
" US_LAST_HOUR";
    
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
    public static String[] tableNamesDB(){
        String[] names = {"ARMAZON","CLIENTE","CONVENIO","CRISTAL",
                           "CUOTAS_CONVENIO","DESCUENTO","DESPACHO","DOCTOR",
                           "EQUIPO","FICHA","HISTORIAL_PAGO","INSTITUCION",
                           "INTERN_STOCK","INVENTARIO","LENTE","MESSAGE",
                           "OFICINA","REGISTRO_BAJAS","TIPO_PAGO","USUARIO"};
        return names;
    }
    public static String[] tablesDB(){
        String[] tables = {ARMAZON,CLIENTE,CONVENIO,CRISTAL,
                           CUOTAS_CONVENIO,DESCUENTO,DESPACHO,DOCTOR,
                           EQUIPO,FICHA,HISTORIAL_PAGO,INSTITUCION,
                           INTERN_STOCK,INVENTARIO,LENTE,MESSAGE,
                           OFICINA,REGISTRO_BAJAS,TIPO_PAGO,USUARIO};
        if(tableNamesDB().length != tables.length){
            OptionPane.showMsg("Error al generar tablas", "Los objetos Array no coinciden\nDetalle: GlobalValuesBD:tablesBD()", 3);
        }
        return tables;
    }
    public static String[] tableDataDB(){
        String[] tables = {COL_ARMAZON,COL_CLIENTE,COL_CONVENIO,COL_CRISTAL,
                           COL_CUOTAS_CONVENIO,COL_DESCUENTO,COL_DESPACHO,COL_DOCTOR,
                           COL_EQUIPO,COL_FICHA,COL_HISTORIAL_PAGO,COL_INSTITUCION,
                           COL_INTERN_STOCK,COL_INVENTARIO,COL_LENTE,COL_MESSAGE,
                           COL_OFICINA,COL_REGISTRO_BAJAS,COL_TIPO_PAGO,COL_USUARIO};
        if(tableNamesDB().length != tables.length){
            OptionPane.showMsg("Error al generar tablas", "Los objetos Array no coinciden\nDetalle: GlobalValuesBD:tablesBD()", 3);
        }
        return tables;
    }
    
    public static void backUpLocalBd(){
        createExcel(new Armazon());
        createExcel(new Cliente());
        createExcel(new Convenio());
        createExcel(new Cristal());
        createExcel(new CuotasConvenio());
        createExcel(new Descuento());
        createExcel(new Despacho());
        createExcel(new Doctor());
        createExcel(new Equipo());
        createExcel(new Ficha());
        createExcel(new HistorialPago());
        createExcel(new Institucion());
        createExcel(new InternStock());
        createExcel(new Lente());
        createExcel(new InternMail());
        createExcel(new Oficina());
        createExcel(new RegistroBaja());
        createExcel(new TipoPago());
        createExcel(new User());
        
        GlobalValuesZipFiles.zipperBackup();
        
    }
    
    private static boolean createExcel(Object type){
        Object[][] res = listaObjetos(type);
        if(res == null || res.length < 1){
            return false;
        }
        if(res.length > 0){
            try {
                GlobalValuesSaveXls.generarExcelRespaldo((String [][])res, nameObjeto(type));
            } catch (WriteException ex) {
                Logger.getLogger(GlobalValuesBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    private static Object[][] listaObjetos(Object type){
        String[] T = tableNamesDB();
        String[] D = tableDataDB();
        int index = getIndex(type);
        if(T.length == D.length){
            return LcBd.select(T[index], D[index], null);
        }
        return null;
    }
    
    private static String nameObjeto(Object type){
        String[] T = tableNamesDB();
        String[] D = tableDataDB();
        int index = getIndex(type);
        if(T.length == D.length){
            return T[index];
        }
        return null;
    }
    
    private static int getIndex(Object type){
        if(type instanceof Armazon){
            return arm-1;
        }
        if(type instanceof Cliente){
            return cli-1;
        }
        if(type instanceof Convenio){
            return cnv-1;
        }
        if(type instanceof Cristal){
            return cri-1;
        }
        if(type instanceof CuotasConvenio){
            return cc-1;
        }
        if(type instanceof Descuento){
            return des-1;
        }if(type instanceof Despacho){
            return dsp-1;
        }if(type instanceof Doctor){
            return doc-1;
        }if(type instanceof Equipo){
            return eq-1;
        }if(type instanceof Ficha){
            return fch-1;
        }if(type instanceof HistorialPago){
            return hp-1;
        }if(type instanceof Institucion){
            return ins-1;
        }if(type instanceof InternStock){
            return ist-1;
        }if(type instanceof Inventario){
            return inv-1;
        }if(type instanceof Lente){
            return len-1;
        }if(type instanceof InternMail){
            return msg-1;
        }if(type instanceof Oficina){
            return of-1;
        }if(type instanceof RegistroBaja){
            return rb-1;
        }if(type instanceof TipoPago){
            return tp-1;
        }if(type instanceof User){
            return usu-1;
        }
        return 0;
    }
}
