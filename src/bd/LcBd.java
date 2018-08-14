/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import fn.GV;
import fn.Log;
import fn.OptionPane;
import fn.globalValues.GlobalValuesBD;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdx
 */
public class LcBd{
    private static Connection conn = null;
    private static String v25 = " varchar(25)";
    private static String v45 = " varchar(45)";
    private static String v100 = " varchar(100)";
    private static String pKey = " not null primary key";
    private static String in = " integer";
    private static String dt = " date";
    private static String sp = " ,";
    private static String className= "LcBd";
    private static String[] T = GlobalValuesBD.tableNamesDB();
    private static String[] C = GlobalValuesBD.tablesDB();
    
    public static Connection crear() {
        Log.setLog(className,Log.getReg());
        if(T.length == C.length){
            for(int i=0;i<T.length;i++){
                createTable(T[i], C[i]);
            }
        }
        return conn;
    }
        
    public static Connection obtener() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Log.setLog(className,Log.getReg());
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection("jdbc:derby://"+GV.getLocalBdUrl()+"/"+GV.getLocalBdName(),GV.getLocalBdUser(),GV.getLocalBdPass());
        if(conn == null)
            OptionPane.showMsg("Error en Base de datos local", "No se pudo obtener la conexion:\nbd.RmBd::obtener(): ERROR BD.\n\nDatelle: "+Log.getLog(), 3);
        return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        Log.setLog(className,Log.getReg());
        if(conn!=null)
            conn.close();
    }
    
    private static void createTable(String tableName, String columns){
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            columns = (columns.startsWith("(")) ? columns.trim():"("+columns.trim();
            columns = (columns.endsWith("))")) ? columns.trim():columns.trim()+")";
            //obtenemos la conexión
            conn = DriverManager.getConnection("jdbc:derby:"+GV.filesPath()+File.separator+"Derby.DB;create=true");
            if (conn!=null){
               String creartabla="create table "+tableName+columns;
               String desc="disconnect;";
               try {
                    PreparedStatement pstm = conn.prepareStatement(creartabla);
                    pstm.execute();
                    pstm.close();

                    PreparedStatement pstm2 = conn.prepareStatement(desc);
                    pstm2.execute();
                    pstm2.close();
                } catch (SQLException ex) {
                    OptionPane.showMsg("Error al crear tabla "+tableName, ex.getLocalizedMessage(),3);
                }
            }
        }catch(SQLException e){
         OptionPane.showMsg("Error al crear tabla "+tableName,e.getMessage() ,  3);
        }catch(ClassNotFoundException e){
           OptionPane.showMsg("Error al crear tabla "+tableName,e.getMessage() ,  3);
        }
    }
    
    public static Object [][] select(String table, String fields, String where){
      int registros = 0;      
      String colname[] = fields.split(",");

      //Consultas SQL
      String q ="SELECT " + fields + " FROM " + table;
      String q2 = "SELECT count(*) as total FROM " + table;
      if(where!=null)
      {
          q+= " WHERE " + where;
          q2+= " WHERE " + where;
      }
       try{
         PreparedStatement pstm = obtener().prepareStatement(q2);
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         OptionPane.showMsg("Error en LCBD", e.getLocalizedMessage(),3);
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LcBd.class.getName()).log(Level.SEVERE, null, ex);
            OptionPane.showMsg("Error en LCBD", "Detalle:\n"+ex.getLocalizedMessage(),3);
        }
    
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][fields.split(",").length];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
      try{
         PreparedStatement pstm = obtener().prepareStatement(q);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){
            for(int j=0; j<=fields.split(",").length-1;j++){
                data[i][j] = res.getString( colname[j].trim() );
            }
            i++;         }
         res.close();
          }catch(SQLException e){
         OptionPane.showMsg("Error en LCBD al obtener datos", ""+e.getLocalizedMessage(),3);
    }   catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error en LCBD al obtener datos", "Detalle:\n"+ex.getLocalizedMessage(),3);
        }
    return data;
 }
}
