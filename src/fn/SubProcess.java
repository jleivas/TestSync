/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import dao.Dao;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.InternMail;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdx
 */
public class SubProcess {
    volatile static boolean ejecucion = true;
    private static String className="SubProcess";
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Dao load = new Dao();
    
    /**
     * Comprueba la conección ainternet cada 5 segundos
     */
    
    public static void isOnline(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                while(ejecucion){
                    GV.chekOnline();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    public static void porcentajeTotal(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                while(ejecucion){
                    System.out.println(GV.porcentajeTotal()+"%");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    
    public static void stopAll(){
        ejecucion = false;
    }

//    public static void sincronizeAll() {
//        if(GV.isOnline()){
//            sincronize(new Inventario());
//            System.out.println("Sincronizar todos los Dao en Subprocesos");
//        }
//        System.out.println("Luego añadir todos los registros en las listas estaticas.");
//        
//    }
    
    public static void sincronizeAll(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                while(ejecucion){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int procesos = 14;
                    GV.resetAllPorcentaje();
                    GV.resetPorcentaje();
                    Dao.sincronize(new Cliente());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Convenio());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Cristal());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Descuento());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Doctor());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Equipo());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Institucion());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new InternMail());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Inventario());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Lente());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new Oficina());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new RegistroBaja());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new TipoPago());
                    GV.calcularPorcentajeTotal(procesos);
                    GV.resetPorcentaje();
                    Dao.sincronize(new User());
                    GV.calcularPorcentajeTotal(procesos);
                    System.out.println("Sincronization complete!");
                }
                
        });
    }
    
    public static void report(String title, String message){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
//            Send mail = new Send();
//            mail.sendReportMail(title, message);
        });
    }
    
    public static void isOnlineDeprecated() {
        GV.chekOnline();
        final Runnable beeper = new Runnable() {
          public void run() { 
              GV.chekOnline(); 
              System.out.println(GV.isOnline());
          }
        };
        final ScheduledFuture<?> beeperHandle =
            scheduler.scheduleAtFixedRate(beeper, 5, 5, SECONDS);
            scheduler.schedule(
                    new Runnable() {
                        public void run() { 
                            beeperHandle.cancel(true); 
                        }
                    }
                    , 60 * 60, SECONDS);
    }
}
