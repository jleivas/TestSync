/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsync;

import fn.UserDao;
import java.sql.SQLException;

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
        
        UserDao load = new UserDao();
        load.deleteUser("root");
        
        
    }
    
}
