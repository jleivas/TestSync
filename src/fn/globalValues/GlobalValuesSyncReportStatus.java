/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesSyncReportStatus {
    private static int PORC = 0;
    
    public static void setPorc(int value){
        PORC = (PORC + value)/2;
    }
    
    public static int getPorc(){
        return PORC;
    }
}
