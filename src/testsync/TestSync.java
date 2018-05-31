/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import dao.ClienteDao;
import dao.CristalDao;
import dao.DescuentoDao;
import dao.DoctorDao;
import dao.UserDao;
import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.User;
import fn.GlobalValues;
import fn.SubProcess;
import fn.date.Cmp;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @author jorge
 */
public class TestSync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException{
        SubProcess.isOnline();
        GlobalValues.LAST_UPDATE = new Date(213312);
        System.out.println(""+GlobalValues.LAST_UPDATE.getYear());
        docs();
        System.out.println(""+GlobalValues.isOnline());
        
    }
    
    private static ArrayList<Object> lista(){
        User u1 = new User(1, "name", "user", "pass", 1, 1, new Date());
        User u2 = new User(2, "name2", "user2", "pass2", 1, 1, new Date());
        ArrayList<Object> list = new ArrayList<>();
        list.add(u2);
        list.add(u1);
        return list;
    }
    
    private static void setValue(Object ob){
        if(ob instanceof User)
            System.out.println("es user");
        if(ob instanceof Descuento)
            System.out.println("es desc");
        if(ob instanceof Cliente)
            System.out.println("es cli");
    }
    private static void cristales() throws InterruptedException {
        SubProcess.isOnline();
        Cristal c1 = new Cristal(0, "CRISTAL-77", 19990, 1, null);
        CristalDao load = new CristalDao();
        load.add(c1);
        load.sincronize();
        for (Cristal cristal : GlobalValues.TMP_LIST_CRISTAL) {
            System.out.println(""+cristal.toString());
        }

    }
    
    private static void descuentos() throws InterruptedException {
        
        Descuento d1 = new Descuento(0, "50 por ciento", "descuento total", 50, 0, 1, null);
        DescuentoDao load = new DescuentoDao();
        load.add(d1);
        load.sincronize();
        for (Descuento des : GlobalValues.TMP_LIST_DESCUENTO) {
            System.out.println(""+des.toString());
        }
    }
    
    private static void clientes() throws InterruptedException {
        
        Cliente c1 = new Cliente("17665703-0", "jorhe", "98383838", "829391293", "josd@gmail.com", "las lomas", "Paine", "Paine", 1, 27, 1, null);
        ClienteDao load = new ClienteDao();
//        load.add(c1);
        load.sincronize();
        for (Cliente cli : GlobalValues.TMP_LIST_CLIENTES) {
            System.out.println(""+cli.toString());
        }
    }
    
    private static void docs() throws InterruptedException {
        
        Doctor doc = new Doctor("17662828-2", "name2", "phone2", "mail2", 1, null);
        DoctorDao load = new DoctorDao();
//        load.add(doc);
        load.sincronize();
        for (Doctor d : GlobalValues.TMP_LIST_DOCTORES) {
            System.out.println(""+d.toString());
        }
    }
    
    
}
