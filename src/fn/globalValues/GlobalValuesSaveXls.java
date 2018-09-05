/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import dao.Dao;
import entities.Cliente;
import fn.GV;
import fn.OptionPane;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesSaveXls {
    public static void generarExcelRespaldo(String[][] entrada, String name) throws WriteException{
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
        
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(GV.directoryFilesPath()+File.separator+"RSP"+File.separator+name+".xls"),conf);
            
            WritableSheet sheet = woorbook.createSheet("Resultado", 0);
            
            WritableFont h = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            
            WritableFont h2 = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
            WritableCellFormat hformat2 = new WritableCellFormat(h2);
            
            for(int i =0; i < entrada.length; i++){//filas
                for(int j=0; j < entrada[i].length;j++){//columnas
                    try {
                        if(i==0 || i==5){
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }else{
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }
                        
                    } catch (WriteException ex) {
                        Logger.getLogger(GlobalValuesSaveXls.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            woorbook.write();
            try {
                woorbook.close();
            } catch (WriteException ex) {
                Logger.getLogger(GlobalValuesSaveXls.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GlobalValuesSaveXls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean saveAllMails() throws ClassNotFoundException {
        Dao load = new Dao();
        List<Object> lista = load.listar("0", new Cliente());
        String sexo = "Sin registrar";
        JFileChooser archivo = new JFileChooser();
        //DATOS DE LA EMPRESA
        //***************
        
        if(lista.size()<1){
            OptionPane.showMsg("Sin datos", "La exportación no se pudo ejecutar: \n"
                    + "No existen registros para guardar.",2);
            return false;
        }
        int resp = archivo.showSaveDialog(archivo);
        if(resp == JFileChooser.APPROVE_OPTION){
            if(lista.size()>0){
                //[filas][columnas]
                int filas = lista.size()+6;
                String [][] entrada = new String[filas][7];
                entrada[0][1] = "Documento generado el "+GV.dateToString(new Date(), "dd de mm del yyyy").replaceFirst("de", "del")+"";
                entrada[1][0] = "Empresa:";
                entrada[1][1] = GV.companyName();
                entrada[2][0] = "Sistema:";
                entrada[2][1] = GV.projectName();
                entrada[3][0] = "Soporte:";
                entrada[3][1] = "www.softdirex.cl";
                entrada[5][0] = "Rut";
                entrada[5][1] = "Nombre";
                entrada[5][2] = "Comuna";
                entrada[5][3] = "Ciudad";
                entrada[5][4] = "Sexo";
                entrada[5][5] = "Teléfono";
                entrada[5][6] = "Correo";
                int contFilas = 6;
                for (Object obj : lista) {
                    Cliente temp = (Cliente)obj;
                    for(int i = 0;i< 7; i++){
                        if(i==0)
                            entrada[contFilas][i] = temp.getCod();
                        else if(i == 1)
                            entrada[contFilas][i] = temp.getNombre();
                        else if(i == 2)
                            entrada[contFilas][i] = temp.getComuna();
                        else if(i == 3)
                            entrada[contFilas][i] = temp.getCiudad();
                        else if(i == 4){
                            if(temp.getSexo()==0)
                                sexo="Sin registrar";
                            if(temp.getSexo()==1)
                                sexo="Femenino";
                            if(temp.getSexo()==2)
                                sexo="Masculino";
                            entrada[contFilas][i] = sexo;
                        }
                        else if(i == 5)
                            entrada[contFilas][i] = (temp.getTelefono1().isEmpty())?temp.getTelefono2():temp.getTelefono1();
                        else if(i == 6)
                            entrada[contFilas][i] = temp.getEmail();
                    }
                    contFilas++;
                }
                String ruta = String.valueOf(archivo.getSelectedFile().toString())+"-["+GV.dateToString(new Date(), "dd-mm-yyyy")+"].xls";
                generarExcel(entrada, ruta);
                OptionPane.showMsg("Generación exitosa", "Archivo creado con exito.",1);
                return true;
            }
        }else if(resp == JFileChooser.CANCEL_OPTION){
            OptionPane.showMsg("No se generó el archivo", "La operacion ha sido cancelada.",2);
        }
        return false;
    }
    
    public static void generarExcel(String[][] entrada, String ruta){
        try {
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
        
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(ruta),conf);
            
            WritableSheet sheet = woorbook.createSheet("Resultado", 0);
            
            WritableFont h = new WritableFont(WritableFont.TIMES, 10,WritableFont.NO_BOLD);
            WritableCellFormat hformat = new WritableCellFormat(h);
            
            WritableFont h2 = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
            WritableCellFormat hformat2 = new WritableCellFormat(h2);
            
            for(int i =0; i < entrada.length; i++){//filas
                for(int j=0; j < entrada[i].length;j++){//columnas
                    try {
                        if(i==0 || i==5){
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat2));
                        }else{
                            sheet.addCell(new jxl.write.Label(j, i, entrada[i][j],hformat));
                        }
                        
                    } catch (WriteException ex) {
                        OptionPane.showMsg("Error inesperado", "Se generó un problema al crear las celdas de la planilla\n"
                                + "Detalle:"+ex, 3);
                    }
                }
            }
            
            woorbook.write();
            try {
                woorbook.close();
            } catch (WriteException ex) {
                OptionPane.showMsg("Error inesperado", "Se generó un problema al intentar cerrar libro de trabajo\n"
                                + "Detalle:"+ex, 3);
            }
            
        } catch (IOException ex) {
            OptionPane.showMsg("Error inesperado", "Se generó un problema al crear planilla Excel\n"
                                + "Detalle:"+ex, 3);
        }
    }
}
