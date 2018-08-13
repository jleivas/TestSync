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
import entities.ficha.Ficha;
import fn.GV;
import fn.SubProcess;
import fn.date.Cmp;
import fn.globalValues.GlobalValuesFunctions;
import fn.globalValues.GlobalValuesSyncReportStatus;
import fn.globalValues.GlobalValuesXmlFiles;
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

    private static String ID_PARAM_IS_USER = "USER/";
    private static String ID_PARAM_IS_CLIENT = "CLIENT/";
    private static Dao load = new Dao();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException, IOException, InstantiationException, IllegalAccessException{
        Ficha ob = new Ficha();
        Object id = ob; 
        if(id instanceof String){
            System.out.println("es String");
        }
        if(!(id instanceof Ficha)){
            System.out.println("no es Ficha");
        }else{
            System.out.println("es ficha");
        }
    }
    
    
    
    public static boolean fichaIdParamIsIdFicha(String arg){
        return true;
    }
    
    public static boolean fichaIdParamIsUSer(String arg){
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
    
}
