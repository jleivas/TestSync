/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import dao.CristalDao;
import dao.UserDao;
import entities.Cristal;
import entities.User;
import fn.GlobalValues;
import fn.SubProcess;
import fn.date.Cmp;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 *
 * @author jorge
 */
public class TestSync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException{
        // TODO code application logic here
//        Date d = new Date(6456564);
//        Date a = new Date();
////        Date b = new Date();
////        System.out.println(""+a.compareTo(d));
//        UserDao load = new UserDao();
//        load.sincronize();
////        User aux = (User)load.get("root");
////        User u1 = new User(0, "Elias", "eli", "admin", 1, 1, null);
////        load.add(u1);
////        User u2 = new User(0, "Alan", "alanx", "1234", 1, 1, null);
////        load.add(u2);
//        load.restore("eli");
//        for (User temp : GlobalValues.TMP_LIST_USERS) {
//            System.out.println(""+temp.toString());
//        }
//        System.out.println(GlobalValues.MAIL_LOG);
//        testear linea 74 en LcBdUser
        cristales();
    }

    private static void cristales() throws InterruptedException {
        SubProcess.isOnline();
        Cristal c1 = new Cristal(0, "CRISTAL-77", 19990, 1, null);
        CristalDao load = new CristalDao();
        load.add(c1);
        load.sincronize();
        for (Cristal cristal : GlobalValues.TMP_LIST_CRISTAL) {
            System.out.println(""+cristal.toString());
        }

        System.exit(0);
    }
    
    
}
