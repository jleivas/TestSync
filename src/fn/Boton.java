/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Frame;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import view.ContentAdmin;
import static view.ContentAdmin.principalAdmin;
import view.VClientes;
import view.VOficinas;
import view.VConvenios;
import view.VCrearFicha;
import view.VCristales;
import view.VDescuentos;
import view.VDoctores;
import view.VInstituciones;
import view.VInventarios;
import view.VLentes;
import view.VMessages;
import view.VTipoPagos;
import view.VUsuarios;

/**
 *
 * @author home
 */
public class Boton {
    private int ancho = 1300;
    private int alto = 650;
    private int locat = 5;
    
//    public void verHistorialPago(int idFicha) throws SQLException, ClassNotFoundException{
//        VHistorialPago p1 = new VHistorialPago();
//        p1.setIdFolio(idFicha);
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }
    
//    public void nuevaFicha() throws SQLException, ClassNotFoundException{
//        crearFicha();
//    }
    
    
    
//    public void abrirFicha(String idFolio) throws SQLException, ClassNotFoundException{
//        VAbrirFicha1 p1 = new VAbrirFicha1();
//        
//        p1.setIdFolio(idFolio);
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        
//        
//        principalAdmin.removeAll();
//        
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }
//    
//    
    
//    
//    
//    public void datosEmpresa() throws SQLException, ClassNotFoundException{
//        VDatosEmpresa p1 = new VDatosEmpresa();
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }
//    
    public void crearFicha() throws SQLException, ClassNotFoundException {
        openView(new VCrearFicha());
    }
    
    public void cristales() throws SQLException, ClassNotFoundException {
//        if(validAccess(GlobalValues.USER.getTipo(),2)){
//            
//        }else{
//            
//        }
        openView(new VCristales());
    }
    
    public void clientes() throws SQLException, ClassNotFoundException{
        openView(new VClientes());
    }
    
    public void convenios() throws SQLException, ClassNotFoundException {
        openView(new VConvenios());
    }
    
    public void descuentos() throws SQLException, ClassNotFoundException{
        openView(new VDescuentos());
    }
    
    public void doctores() throws SQLException, ClassNotFoundException{
        openView(new VDoctores());
    }
    
    public void instituciones() throws SQLException, ClassNotFoundException{
        openView(new VInstituciones());
    }
    
    public void inventarios() throws SQLException, ClassNotFoundException {
        openView(new VInventarios());
    }
    
    public void lentes() throws SQLException, ClassNotFoundException {
        openView(new VLentes());
    }
    
    public void mensajes() {
        GV.cursor();
        openView(new VMessages());
    }
    
    public void oficinas() throws SQLException, ClassNotFoundException {
        openView(new VOficinas());
    }
    
    public void tipoPagos() throws SQLException, ClassNotFoundException{
        openView(new VTipoPagos());
    }
    
    public void usuarios() throws SQLException, ClassNotFoundException {
        openView(new VUsuarios());
    }
    
    private void openView(JPanel p1){
        
        
        try{
            p1.setSize(ancho, alto);
            p1.setLocation(locat, locat);
            principalAdmin.removeAll();
            principalAdmin.add(p1,BorderLayout.CENTER);
            principalAdmin.revalidate();
            principalAdmin.repaint();
        }catch(Exception ex){
            OptionPane.showMsg("Error inesperado", "No se ha podido abrir la ventana solicitada, \n"
                    + "se enviará un reporte para solucionar este problema,\n"
                    + "póngase en contacto con su proveedor de software.", JOptionPane.ERROR_MESSAGE);
        }
        principalAdmin.setCursor(Cursor.getDefaultCursor());
    }
    
//    
    
//    
//    public void fichas() throws SQLException, ClassNotFoundException{
//        VMostrarFichas p1 = new VMostrarFichas();
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }
//    
//    public void misFichas() throws SQLException, ClassNotFoundException {
//        VMisFichas p1 = new VMisFichas();
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }
//
//    public void barraProgreso(ArrayList<Ficha> lista, Info empresa, int funcion) throws InterruptedException {
//        Progreso p1 = new Progreso();
//        p1.setLista(lista, empresa,funcion);
//        p1.setSize(449, 250);
//        p1.setLocation(locat, locat);
//        p1.setVisible(true);
//    }
//
    
//
//
//    public void registroBajas() throws SQLException, ClassNotFoundException {
//        VRegistroBajas p1 = new VRegistroBajas();
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }

    
//
//    public void reporteVentas() throws SQLException, ClassNotFoundException {
//        VReporteFichas p1 = new VReporteFichas();
//        p1.setSize(ancho, alto);
//        p1.setLocation(locat, locat);
//        principalAdmin.removeAll();
//        principalAdmin.add(p1,BorderLayout.CENTER);
//        principalAdmin.revalidate();
//        principalAdmin.repaint();
//    }

    

}
