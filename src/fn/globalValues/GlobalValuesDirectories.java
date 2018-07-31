/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import static fn.GV.getStr;
import java.io.File;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesDirectories {
    /* Direcciones de fichero*/
    public static String LOCAL_PATH = System.getProperty("user.dir")+File.separator;
    public static String FILES_PATH = LOCAL_PATH+"files"+File.separator;
    
    
    public static String getFilesPath(){
        return getStr(FILES_PATH);
    }
    
    public static String getLocalPath(){
        return getStr(LOCAL_PATH);
    }
}