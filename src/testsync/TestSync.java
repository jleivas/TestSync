/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import dao.UserDao;
import entities.User;
import fn.GlobalValues;
import fn.date.Cmp;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jorge
 */
public class TestSync {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        // TODO code application logic here
//        Date d = new Date(6456564);
//        Date a = new Date();
//        Date b = new Date();
//        System.out.println(""+a.compareTo(d));
        UserDao load = new UserDao();
        load.sincronize();
//        User aux = (User)load.get("root");
//        if(aux != null){
//            aux.setEstado(1);
//            load.update(aux);
//        }
        
        for (User temp : GlobalValues.TMP_LIST_USERS) {
            System.out.println(""+temp.toString());
        }
//        testear linea 74 en LcBdUser
    }
    
}
