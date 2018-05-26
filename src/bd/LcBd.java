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
public class LcBd {
    private static Connection conn = null;
    
    public static Connection obtener() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection("jdbc:derby://"+GlobalValues.getLocalBdUrl()+"/"+GlobalValues.getLocalBdName(),GlobalValues.getLocalBdUser(),GlobalValues.getLocalBdPass());
        if(conn == null)
            OptionPane.showMsg("Error en Base de datos local", "No se pudo obtener la conexion:/nbd.RmBd::obtener(): ERROR BD.", JOptionPane.ERROR_MESSAGE);
        else
            System.out.println("bd.LcBd::obtener(): EXITO BD.");
        return conn;
    }
    
    public static void cerrar() throws SQLException
    {
        System.out.println("bd.LcBd::cerrar()");
        if(conn!=null)
            conn.close();
    }
}
