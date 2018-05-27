/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jorge
 */
public class OptionPane {
    private static int ancho = 549;
    private static int alto = 220;
    private static int locat = 5;
    private static String className = "OptionPane";
    
    
    public static void showMsg(String title, String message, int statusMsg){
        Log.setLog(className,Log.getReg());
        JOptionPane.showMessageDialog(null, message, title, statusMsg);
    }

    

}
