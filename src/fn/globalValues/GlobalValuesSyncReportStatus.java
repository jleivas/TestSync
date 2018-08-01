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
    private static int SUMA_PORC = 0;
    private static int LIMIT = 0;
    private static int TOTAL = 0;
    private static int SUMA_TOTAL = 0;
    private static int PROCESOS = 0;
    
    public static void resetPorc(){
        PORC = 0;
        SUMA_PORC = 0;
    }
    
    public static void resetAll(){
        PORC = 0;
        SUMA_PORC = 0;
        LIMIT = 0;
        TOTAL = 0;
        SUMA_TOTAL = 0;
        PROCESOS = 0;
    }
    
    public static void setPorc(int value){
        PORC = (PORC + value)/2;
    }
    
    public static void calcularPorcentaje(int limit){
        LIMIT = limit;
        SUMA_PORC++;
        PORC = (SUMA_PORC * 100)/LIMIT;
        if(PORC > 100){
            PORC = 100;
        }
    }
    
    public static void calcularPorcentajeTotal(int totalProcesos){
        PROCESOS = totalProcesos;
        SUMA_TOTAL++;
        TOTAL = (SUMA_TOTAL * 100)/ PROCESOS;
        if(TOTAL > 100){
            TOTAL = 100;
        }
    }
    
    public static int getPorc(){
        return PORC;
    }
    
    public static int getTotal(){
        return TOTAL;
    }
}
