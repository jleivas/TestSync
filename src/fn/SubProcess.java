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
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sdx
 */
public class SubProcess {
    volatile static boolean ejecucion = true;
    volatile static boolean pause = false;
    private static String className="SubProcess";
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static String defaultText = "";
    
    Dao load = new Dao();
    
    /**
     * Comprueba la conección ainternet cada 5 segundos
     */
    
    public static void isOnline(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
                while(ejecucion){
                    if(!pause){
                        GV.chekOnline();
                    }
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
    
    
    public static void lblSyncStatus(JLabel txtTitle) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String text = txtTitle.getText();
            if(!text.contains("%")){
                defaultText = text;
            }
            int porcentaje = 0;
                while(ejecucion){
                    porcentaje = GV.porcentajeTotal();
                    if(porcentaje > 0){
                        txtTitle.setText("Sincronizando dependencias... ("+porcentaje+"%)");
                    }else{
                        txtTitle.setText(defaultText);
                    }
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
        Boton boton = new Boton();
        boton.barraProgreso();
        int procesos = 14;
        GV.resetAllPorcentaje();
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando clientes...");
        Dao.sincronize(new Cliente());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando convenios...");
        Dao.sincronize(new Convenio());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando cristales...");
        Dao.sincronize(new Cristal());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando descuentos...");
        Dao.sincronize(new Descuento());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando datos de profesionales...");
        Dao.sincronize(new Doctor());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando equipos...");
        Dao.sincronize(new Equipo());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando instituciones...");
        Dao.sincronize(new Institucion());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando mensajes...");
        Dao.sincronize(new InternMail());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando inventarios...");
        Dao.sincronize(new Inventario());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando armazones...");
        Dao.sincronize(new Lente());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando locales...");
        Dao.sincronize(new Oficina());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando registros...");
        Dao.sincronize(new RegistroBaja());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando registros de pago...");
        Dao.sincronize(new TipoPago());
        GV.calcularPorcentajeTotal(procesos);
        GV.resetPorcentaje();
        GV.setReporte("Sincronizando usuarios...");
        Dao.sincronize(new User());
        GV.calcularPorcentajeTotal(procesos);
        GV.setLastUpdate(new Date());
        GV.resetAllPorcentaje();
    }
    
    public static void suspendConnectionOnline(){
        GV.isOnline(false);
        pause = true;
    }
    
    public static void activateConnectionOnline(){
        GV.isOnline(true);
        pause = false;
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
    
    public static boolean ejecucion(){
        return ejecucion;
    }
}
