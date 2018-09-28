/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dao.Dao;
import entities.ficha.Ficha;
import fn.OptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
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
public class TestSync2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Dao load = new Dao();
        Ficha ficha = new Ficha();
        try {
            ficha = (Ficha)load.get("2/2", 0, new Ficha());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TestSync2.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = null;
        JasperPrint jsp = null;
        FichaDataSource dt = new FichaDataSource();
        dt.addFicha(ficha);
        try{
            is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"ficha.jrxml");
        }catch(FileNotFoundException e){
            OptionPane.showMsg("No se puede obtener el recurso", 
                    "Ocurrió un error al intentar abrir el formato de impresión\n"
                            + e.getMessage(), 3);
        }
        
        
        try{
            JasperDesign jsd = JRXmlLoader.load(is);
            JasperReport jsrp = JasperCompileManager.compileReport(jsd);
            jsp = JasperFillManager.fillReport(jsrp, null,dt);
            JasperViewer viewer = new JasperViewer(jsp, false); //Se crea la vista del reportes
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            viewer.setVisible(true); //Se vizualiza el reporte
        }catch( JRException e){
            OptionPane.showMsg("No se puede visualizar el recurso", 
                    "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                            + e.getMessage(), 3);
        }
    }
    
    
}
