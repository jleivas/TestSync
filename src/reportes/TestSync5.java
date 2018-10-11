/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dao.Dao;
import entities.Convenio;
import entities.User;
import fn.GV;
import fn.OptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jorge
 */
public class TestSync5 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{
            
            String title = "";
            String subtitle = "";
            Dao load = new Dao();
            
            InputStream is = null;
            GV.setUser((User)load.get("root", 0, new User()));
            SalesFichaRecursoDatos dt = new SalesFichaRecursoDatos();
            List<Object> fichas = GV.listarFichas(GV.strToDate("01-08-2018"), new Date(), 0, null, null);
            dt.createReport(fichas, "titulo", "company", "www", "address", "contacts data");
            try{
                is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"reporteVentas.jrxml");
            }catch(FileNotFoundException e){
                OptionPane.showMsg("No se puede obtener el recurso",
                        "Ocurrió un error al intentar abrir el formato de impresión\n"
                                + e.getMessage(), 3);
            }
            
            
            openView(is,dt);
        }catch(SQLException ex){
            Logger.getLogger(TestSync5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestSync5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TestSync5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TestSync5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void openView(InputStream is, JRDataSource fcr){
        try{
                JasperDesign jsd = JRXmlLoader.load(is);
                JasperReport jsrp = JasperCompileManager.compileReport(jsd);
                JasperPrint jsp = JasperFillManager.fillReport(jsrp, null,fcr);
                JasperViewer viewer = new JasperViewer(jsp, false); //Se crea la vista del reportes
                viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
                viewer.setVisible(true); //Se vizualiza el reporte
//            generateReport(jsp, true, "src"+File.separator+"reportes"+File.separator+"fichasConvenio.xls");
            }catch( JRException e){
                OptionPane.showMsg("No se puede visualizar el recurso",
                        "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                                + e.getMessage(), 3);
            }
    }
    
    
}
