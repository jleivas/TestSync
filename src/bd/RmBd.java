/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import fn.GlobalValues;
import fn.OptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sdx
 */
public class RmBd {
    private static Connection conn = null;
    
    public static Connection obtener() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://"+GlobalValues.getRemoteBdUrl()+"/"+GlobalValues.getRemoteBdName(),GlobalValues.getRemoteBdUser(),GlobalValues.getRemoteBdPass());
        if(conn == null)
            OptionPane.showMsg("Error en Base de datos remota", "No se pudo obtener la conexion:/nbd.RmBd::obtener(): ERROR BD.", JOptionPane.ERROR_MESSAGE);
        else
            System.out.println("bd.RmBd::obtener(): EXITO BD.");
       return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        System.out.println("bd.RmBd::cerrar()");
        if(conn!=null)
            conn.close();
    }
}
