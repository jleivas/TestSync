/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

/**
 *
 * @author jorge
 */
public class Log {
    public static String getReg(){
        return "::"+new Exception().getStackTrace()[1].getMethodName();
    }

    public static void setLog(String className, String reg) {
        if(GV.MAIL_LOG.length() < 2)
            GV.MAIL_LOG = "Registro del sistema:\n"+className+reg;
        else
            GV.MAIL_LOG = GV.MAIL_LOG+"\n"+className+reg;
    }

    public static String getLog() {
        if(GV.MAIL_LOG.length() > 100)
            return "Registro del sistema:\n..."+GV.MAIL_LOG.substring(50);
        return GV.MAIL_LOG;
    }
}
