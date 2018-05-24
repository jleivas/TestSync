/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author sdx
 */
public class Cmp {
    public static boolean isOnline(){
        String comando = "ping -c 1 google.com";
        try{
            return (Runtime.getRuntime().exec (comando).waitFor() == 0);
        }catch(IOException | InterruptedException e){
            return false;
        }
        
//        try{
//            if(new Socket("www.softdirex.cl", 80).isConnected()){
//              return true;
//            }
//        }catch(Exception e){
//            return false;
//        }
//        return false;
    }
}
