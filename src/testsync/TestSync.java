/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import dao.ClienteDao;
import dao.CristalDao;
import dao.DescuentoDao;
import dao.UserDao;
import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.User;
import fn.GlobalValues;
import fn.SubProcess;
import fn.date.Cmp;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException{
        SubProcess.isOnline();
        cristales();
        descuentos();
        clientes();
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
        
        Descuento d1 = new Descuento(0, "100 por ciento", "descuento total", 100, 0, 1, null);
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
        load.add(c1);
        load.sincronize();
        for (Cliente cli : GlobalValues.TMP_LIST_CLIENTES) {
            System.out.println(""+cli.toString());
        }
    }
    
    
}
