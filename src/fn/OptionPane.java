/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    private static boolean confirm = false;
    
    public static void showPanel(javax.swing.JPanel p1, String title){
        GV.INFOPANEL.lblTitle.setText(title);
        GV.INFOPANEL.setVisible(true);
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
        GV.MSG_STATUS = statusMsg;
        OpanelMessage p1 = new OpanelMessage();
        GV.INFOPANEL.lblTitle.setText(title);
        if(p1 instanceof OpanelMessage){
            ((OpanelMessage) p1).lblTitle.setText(title);
            ((OpanelMessage) p1).lblMessage.setText(message);
        }
        
        GV.INFOPANEL.setVisible(true);
        p1.setSize(ancho, alto);
        p1.setLocation(locat, locat);
        
        OpanelContent.removeAll();
        OpanelContent.add(p1,BorderLayout.CENTER);
        OpanelContent.revalidate();
        OpanelContent.repaint();
    }

    public static void closeInfoPanel() {
        GV.INFOPANEL.setVisible(false);
    }

    public static boolean getConfirmation(String title, String message, int statusMsg){
        int resp = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(resp == JOptionPane.YES_OPTION)
            return true;
        return false;
    }
    public static boolean getConfirm(){
        if(confirm){
            setConfirm(false);
            return true;
        }
        return confirm;
    }
    private static void showConfirm(String title, String message, int statusMsg){
        if(title.length() > 40){
            message = title.toUpperCase()+"\n\n"+message;
            title = title.substring(0,38)+"...";
        }
        GV.MSG_STATUS = statusMsg;
        OpanelConfirm p1 = new OpanelConfirm();
        GV.INFOPANEL.lblTitle.setText(title);
        if(p1 instanceof OpanelConfirm){
            ((OpanelConfirm) p1).lblTitle.setText(title);
            ((OpanelConfirm) p1).lblMessage.setText(message);
        }
        
        GV.INFOPANEL.setVisible(true);
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
