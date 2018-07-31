/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import entities.Cliente;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Institucion;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import java.util.ArrayList;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesEntities {
    public static User USER;
    public static Oficina OFICINA;
    
    
    public static void setOficina(Oficina oficina){
        OFICINA = oficina;
    }

    public static String getNombreOficina() {
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
}
