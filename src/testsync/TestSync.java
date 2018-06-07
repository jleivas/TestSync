/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import dao.Dao;
import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Oficina;
import entities.User;
import fn.GlobalValues;
import fn.SubProcess;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import sync.entities.Local;

/**
 *
 * @author jorge
 */
public class TestSync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException, UnknownHostException, IOException, InstantiationException, IllegalAccessException{
//        Oficina of = new Oficina(1, "nom", "dir", "ciudad", "tel1", "tel2", "email", "web", 1, null);
//        System.out.println(""+of.toString());
//        Cristal cri = new Cristal(1, "nom", 2000, 1, null);
//        System.out.println(""+cri.toString());
//        Cliente cli = new Cliente("123312", "sc", "sd", "adlkj", "alihl", "adlfk", "hadsfo", "aslfnk", 0, 0, 0, null);
//        System.out.println(""+cli.toString());
//        Descuento ds = new Descuento(1, "lakdjsljkd", "asdljdasl", 0, 0, 0, null);
//        System.out.println(""+ds.toString());
//        Doctor dc = new Doctor("sdlkj", "dalj", "alsdkjadjkls", "asdlj", 23, null);
//        Doctor dc2 = new Doctor("segundo", "otro", "okey", "ultim", 24, new Date(124244123));
//        System.out.println(""+dc.toString());
//        System.out.println(""+dc2.toString());
//        Local load = new Local();
        String g=" jorge sd";
        if(g.contains("jorge"))
            System.out.println("yes");
        else
            System.out.println("no");
//        SubProcess.isOnline();
//        sync.Cmp.getPublicIp();
//        GlobalValues.LAST_UPDATE = new Date(213312);
//        GlobalValues.IS_ONLINE = true;
//        System.out.println(""+GlobalValues.LAST_UPDATE.getYear());
//        Dao load = new Dao();
//        
//        oficinas();
//        load.sincronize(new Oficina());
//        cristales();
//        load.sincronize(new Cristal());
//        clientes();
//        load.sincronize(new Cliente());
//        descuentos();
//        load.sincronize(new Descuento());
//        docs();
    }
    
    private static ArrayList<Object> lista(){
        User u1 = new User(1, "name", "user","name@itc.cl", "pass", 1, 1, new Date());
        User u2 = new User(2, "name2", "user2","name2@itc.cl", "pass2", 1, 1, new Date());
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
    private static void cristales() throws InterruptedException, InstantiationException, IllegalAccessException {
        SubProcess.isOnline();
        Cristal c1 = new Cristal(0, "CRISTAL-8080", 19990, 1, null);
        Dao load = new Dao();
        load.add(c1);
        for (Cristal cristal : GlobalValues.TMP_LIST_CRISTAL) {
            System.out.println(""+cristal.toString());
        }

    }
    
    private static void descuentos() throws InterruptedException, InstantiationException, IllegalAccessException {
        
        Descuento d1 = new Descuento(0, "XX por ciento", "descuento total", 50, 0, 1, null);
        Dao load = new Dao();
        load.add(d1);
        for (Descuento des : GlobalValues.TMP_LIST_DESCUENTO) {
            System.out.println(""+des.toString());
        }
    }
    
    private static void clientes() throws InterruptedException, InstantiationException, IllegalAccessException {
        
        Cliente c1 = new Cliente("18188881-0", "amamam", "98383838", "829391293", "josd@gmail.com", "las lomas", "Paine", "Paine", 1, 27, 1, null);
        Dao load = new Dao();
        load.add(c1);
        for (Cliente cli : GlobalValues.TMP_LIST_CLIENTES) {
            System.out.println(""+cli.toString());
        }
    }
    
    private static void docs() throws InterruptedException, InstantiationException, IllegalAccessException {
        
        Doctor doc = new Doctor("2222222-2", "name3", "phone3", "mail3", 1, null);
        Dao load = new Dao();
        load.add(doc);
        for (Doctor d : GlobalValues.TMP_LIST_DOCTORES) {
            System.out.println(""+d.toString());
        }
    }
    
    private static void oficinas() throws InterruptedException, InstantiationException, IllegalAccessException {
        
        Oficina of = new Oficina(1, "Stgo", "Mcyver", "Stgo CEntro", "9983748239", "2312341342","info@itc.cl", "www.itc.cl", 1, null);
        Dao load = new Dao();
        of.toString();
        load.add(of);
        for (Oficina o : GlobalValues.TMP_LIST_OFICINAS) {
            System.out.println(""+o.toString());
        }
    }
    
    
}
