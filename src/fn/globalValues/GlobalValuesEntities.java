/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;


import dao.Dao;
import entities.Oficina;
import entities.User;
import entities.ficha.Ficha;
import fn.GV;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesEntities {
    public static User USER;
    public static Oficina OFICINA;
    public static Ficha stFicha = new Ficha();
    
    
    public static Ficha getFicha(){
        return stFicha;
    }
    
    public static void setFicha(Ficha value){
        stFicha = value;
    }
    public static void setOficina(Oficina oficina){
        OFICINA = oficina;
    }
    
    public static boolean setOficina(String nombre){
        Dao load = new Dao();
        try {
            Oficina temp = (Oficina)load.get(GV.getStr(nombre), 0, new Oficina());
            if(temp != null){
                setOficina(temp);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GlobalValuesEntities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getNombreOficina() {
        if(OFICINA != null){
            if(!OFICINA.getNombre().isEmpty()){
                return OFICINA.getNombre();
            }
        }
        return "Ninguna";
    }
    
    public static String getLblNombreOficina() {
        if(OFICINA != null){
            if(!OFICINA.getNombre().isEmpty()){
                return "Asignada a este equipo: "+OFICINA.getNombre();
            }
        }
        return "Asignada a este equipo: Ninguna";
    }

    static int getTipoUsuario() {
        if(USER != null){
            return USER.getTipo();
        }
        return 0;
    }
    
    public static boolean tipoUserSuperAdmin(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    public static boolean tipoUserAdmin(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 2 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    public static boolean tipoUserIventario(){
        int tipoUsuario = getTipoUsuario();
        if(tipoUsuario == 1 || tipoUsuario == 2 || tipoUsuario == 4 || tipoUsuario == 7){
            return true;
        }
        return false;
    }
    
    public static void setSessionUser(User user){
        USER = user;
    }
    
    public static User getSessionUser(){
        return USER;
    }

    public static void clearFicha() {
        stFicha =  new Ficha();
    }
}
