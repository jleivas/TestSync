/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import dao.Dao;
import entities.ficha.Ficha;
import fn.GV;
import fn.OptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jorge
 */
public class TestSync3 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Dao load = new Dao();
        Ficha ficha1 = new Ficha();
        Ficha ficha2 = new Ficha();
        Ficha ficha3 = new Ficha();
        Ficha ficha4 = new Ficha();
        Ficha ficha5 = new Ficha();
        Ficha ficha6 = new Ficha();
        Ficha ficha7 = new Ficha();
        try {
            ficha1 = (Ficha)load.get("1/2", 0, new Ficha());
            ficha2 = (Ficha)load.get("2/2", 0, new Ficha());
            ficha3 = (Ficha)load.get("3/2", 0, new Ficha());
            ficha4 = (Ficha)load.get("4/2", 0, new Ficha());
            ficha5 = (Ficha)load.get("5/2", 0, new Ficha());
            ficha6 = (Ficha)load.get("6/2", 0, new Ficha());
            ficha7 = (Ficha)load.get("7/2", 0, new Ficha());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TestSync3.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = null;
        JasperPrint jsp = null;
        FichaRecursoDatos dt = new FichaRecursoDatos();
        dt.addTitle("Reporte de fichas por convenio Convenio 3", "fecha de emision "+GV.dateToString(new Date(), "dd.mm.yyyy"));
        dt.addFicha(ficha1);
        dt.addFicha(ficha2);
        dt.addFicha(ficha3);
        dt.addFicha(ficha4);
        dt.addFicha(ficha5);
        dt.addFicha(ficha6);
        dt.addFicha(ficha7);
        
        try{
            is = new FileInputStream("src"+File.separator+"reportes"+File.separator+"fichasCnv.jrxml");
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
//            generateReport(jsp, true, "src"+File.separator+"reportes"+File.separator+"fichasConvenio.xls");
        }catch( JRException e){
            OptionPane.showMsg("No se puede visualizar el recurso", 
                    "Ocurrió un error al intentar abrir visualización del formato de impresión\n"
                            + e.getMessage(), 3);
        }
    }
    
    public static void generateReport(JasperPrint report, boolean isExcel, String saveTo) throws JRException{
  JRExporter exporter = null;
  if (isExcel) {
    exporter = new JRXlsExporter();
    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
    exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
    //we set the one page per sheet parameter here
    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
  } else {
    exporter = new JRPdfExporter();     
  }
  exporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, saveTo);
  exporter.exportReport();
}
    
    
}
