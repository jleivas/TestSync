/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import static view.opanel.OPanel.OpanelContent;
import view.opanel.OpanelConfirm;
import view.opanel.OpanelMessage;

/**
 *
 * @author jorge
 */
public class OptionPane {
    private static int ancho = 549;
    private static int alto = 220;
    private static int locat = 5;
    private static String className = "OptionPane";
    private static boolean confirm = true;
    
    public static void showPanel(javax.swing.JPanel p1, String title){
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }
    public static void showMsg(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GlobalValues.MSG_STATUS = statusMsg;
        OpanelMessage p1 = new OpanelMessage();
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        if(p1 instanceof OpanelMessage){
            ((OpanelMessage) p1).lblTitle.setText(title);
            ((OpanelMessage) p1).lblMessage.setText(message);
        }
        
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }

    public static void closeOPanel() {
        GlobalValues.INFOPANEL.setVisible(false);
    }

    public static boolean getConfirmation(String title, String message, int statusMsg){
        showConfirm(title, message, statusMsg);
        return confirm;
    }
    private static void showConfirm(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GlobalValues.MSG_STATUS = statusMsg;
        OpanelConfirm p1 = new OpanelConfirm();
        GlobalValues.INFOPANEL.lblTitle.setText(title);
        if(p1 instanceof OpanelConfirm){
            ((OpanelConfirm) p1).lblTitle.setText(title);
            ((OpanelConfirm) p1).lblMessage.setText(message);
        }
        
        GlobalValues.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }
    public static void setConfirm(boolean param){
        confirm = param;
    }

}
