/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;
import bd.RmBd;
import entities.Cliente;
import entities.Convenio;
import entities.Cristal;
import entities.Descuento;
import entities.Doctor;
import entities.Equipo;
import entities.Institucion;
import entities.InternMail;
import entities.Inventario;
import entities.Lente;
import entities.Oficina;
import entities.RegistroBaja;
import entities.TipoPago;
import entities.User;
import entities.ficha.Armazon;
import entities.ficha.Despacho;
import entities.ficha.EtiquetFicha;
import entities.ficha.Ficha;
import entities.ficha.HistorialPago;
import entities.ficha.ResF;
import fn.GV;
import fn.Log;
import fn.OptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sync.InterfaceSync;

/**
 *
 * @author sdx
 */
public class Remote implements InterfaceSync{
    private static String className = "Remote";
    @Override
    public boolean add(Object objectParam) {
        Log.setLog(className, Log.getReg());
        try{
            if(objectParam == null)
                return false;
            if(objectParam instanceof Ficha){
                Ficha object = (Ficha)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT fch_id FROM ficha WHERE fch_id='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Ficha: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Armazon){
                Armazon object = (Armazon)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT arm_id FROM armazon WHERE arm_id='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "armazon: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Cliente){
                Cliente object = (Cliente)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT cli_rut FROM cliente WHERE cli_rut='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cliente: " + object.getNombre()+ "\nId: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Convenio){
                Convenio object = (Convenio)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT cnv_id FROM convenio WHERE cnv_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cliente: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Cristal){
                Cristal object = (Cristal)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT cri_id FROM cristal WHERE cri_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Cristal: " + object.getNombre() + "\nId: " + object.getId() + "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Descuento){
                Descuento object = (Descuento)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT des_id FROM descuento WHERE des_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Descuento: " + object.getNombre() + "\nId: " + object.getId() + "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Despacho){
                Despacho object = (Despacho)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT dsp_id FROM despacho WHERE dsp_id='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "despacho: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Doctor){
                Doctor object = (Doctor)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT doc_rut FROM doctor WHERE doc_rut='" + object.getCod() + "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Profesional: " + object.getNombre()+ "\nId: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Equipo){
                Equipo object = (Equipo)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT eq_id FROM equipo WHERE eq_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Equipo: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof HistorialPago){
                HistorialPago object = (HistorialPago)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT hp_id FROM historial_pago WHERE hp_id='" + object.getCod()+ "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Historial de pago: " + object.getCod()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Institucion){
                Institucion object = (Institucion)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT ins_id FROM institucion WHERE ins_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Institucion: " + object.getNombre()+ "\nId: " + object.getId() + "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof InternMail){
                InternMail object = (InternMail)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT msg_id FROM message WHERE msg_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Mensaje: " + object.getAsunto()+ "\nId: " + object.getId() + "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Inventario){
                Inventario object = (Inventario)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT inv_id FROM inventario WHERE inv_id="+object.getId()+"");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return update(object);
                }  
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlInsert(object)
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();

                    return true;
                }
            }
            if(objectParam instanceof Lente){
                Lente object = (Lente)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT len_id FROM lente WHERE len_id='" + object.getCod()+ "'");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Lente: " + object.getCod()+ "\nId: " + object.getCod() + "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof Oficina){
                Oficina object = (Oficina)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT of_id FROM oficina WHERE of_id=" + object.getId() + "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Oficina: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof RegistroBaja){
                RegistroBaja object = (RegistroBaja)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT rb_id FROM registro_bajas WHERE rb_id=" + object.getCod()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Registro de Bajas: " + object.getCod()+ "\n\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof TipoPago){
                TipoPago object = (TipoPago)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT tp_id FROM tipo_pago WHERE tp_id=" + object.getId()+ "");
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();

                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Tipo de Pago: " + object.getNombre()+ "\nId: " + object.getId()+ "\nNo se pudo insertar.", 3);
                return false;
            }
            if(objectParam instanceof User){
                User object = (User)objectParam;
                if (object != null) {
                    PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT us_id FROM usuario WHERE us_id=" + object.getId());
                    ResultSet datos = consulta.executeQuery();
                    while (datos.next()) {
                        RmBd.cerrar();
                        return update(object);
                    }
                    PreparedStatement insert = RmBd.obtener().prepareStatement(
                            sqlInsert(object)
                    );
                    if (insert.executeUpdate() != 0) {
                        RmBd.cerrar();
                        //OptionPane.showMsg("Operación realizada correctamente", "Usuario: "+object.getUsername()+"\nId: "+object.getId()+"\nAgregado correctamente.", 1);
                        return true;
                    }
                }
                OptionPane.showMsg("Error inseperado en la operación", "Usuario: " + object.getUsername() + "\nId: " + object.getId() + "\nNo se pudo insertar.", 3);
                return false;
            }else{
                OptionPane.showMsg("Error inseperado en la operación", "El objeto no se pudo insertar.\n\n"+className+" no soporta el tipo de registro enviado.", 3);
                return false;
            }
        }catch( SQLException | ClassNotFoundException ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public boolean update(Object objectParam) {
        Log.setLog(className, Log.getReg());
        Date dsp_fecha = new Date();
        int hour = 0;
        try{
            if(objectParam == null)
                return false;
            if(objectParam instanceof Ficha){
                Ficha object = (Ficha)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM ficha WHERE fch_id='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("fch_last_update");
                        hour = datos.getInt("fch_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Armazon){
                Armazon object = (Armazon)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM armazon WHERE arm_id='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("arm_last_update");
                        hour = datos.getInt("arm_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Cliente){
                Cliente object = (Cliente)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM cliente WHERE cli_rut='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("cli_last_update");
                        hour = datos.getInt("cli_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }if(objectParam instanceof Convenio){
                Convenio object = (Convenio)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM convenio WHERE cnv_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("cnv_last_update");
                        hour = datos.getInt("cnv_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Cristal){
                Cristal object = (Cristal)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM cristal WHERE cri_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("cri_last_update");
                        hour = datos.getInt("cri_last_hour");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object)
                );
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Descuento){
                Descuento object = (Descuento)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM descuento WHERE des_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("des_last_update");
                        hour = datos.getInt("des_last_hour");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Despacho){
                Despacho object = (Despacho)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM despacho WHERE dsp_id='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("dsp_last_update");
                        hour = datos.getInt("dsp_last_hour");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Doctor){
                Doctor object = (Doctor)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM doctor WHERE doc_rut='" + object.getCod() + "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("doc_last_update");
                        hour = datos.getInt("doc_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Equipo){
                Equipo object = (Equipo)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM equipo WHERE eq_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("eq_last_update");
                        hour = datos.getInt("eq_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof HistorialPago){
                HistorialPago object = (HistorialPago)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM historial_pago WHERE hp_id='" + object.getCod()+ "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("hp_last_update");
                        hour = datos.getInt("hp_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Institucion){
                Institucion object = (Institucion)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM institucion WHERE ins_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("ins_last_update");
                        hour = datos.getInt("ins_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof InternMail){
                InternMail object = (InternMail)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM message WHERE msg_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("msg_last_update");
                        hour = datos.getInt("msg_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Inventario){
                Inventario object = (Inventario)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM inventario WHERE inv_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("inv_last_update");
                        hour = datos.getInt("inv_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Lente){
                Lente object = (Lente)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM lente WHERE len_id='" + object.getCod()+ "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("len_last_update");
                        hour = datos.getInt("len_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof Oficina){
                Oficina object = (Oficina)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM oficina WHERE of_id=" + object.getId() + "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("of_last_update");
                        hour = datos.getInt("of_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof RegistroBaja){
                RegistroBaja object = (RegistroBaja)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM registro_bajas WHERE rb_id='" + object.getCod()+ "'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("rb_last_update");
                        hour = datos.getInt("rb_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof TipoPago){
                TipoPago object = (TipoPago)objectParam;
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM tipo_pago WHERE tp_id=" + object.getId()+ "");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("tp_last_update");
                        hour = datos.getInt("tp_last_hour");
                    } catch (SQLException e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle: " + e.getMessage() + "\n" + Log.getLog(), 3);
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        RmBd.cerrar();
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
            if(objectParam instanceof User){
                User object = (User)objectParam;
                if (object == null) {
                    return false;
                }
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM usuario WHERE us_id=" + object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    
                    try {
                        dsp_fecha = datos.getDate("us_last_update");
                        hour = datos.getInt("us_last_hour");
                    } catch (Exception e) {
                        OptionPane.showMsg("Error al convertir fecha", "Se cayó al intentar convertir la fecha.\nDetalle:\n" + Log.getLog(), 3);
                    }
                    if (dsp_fecha == null) {
                        dsp_fecha = new Date();
                    }
                    if (!fn.date.Cmp.objectIsNew(object.getLastUpdate(),object.getLastHour(), dsp_fecha,hour)) {
                        return false;
                    }
                }
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        sqlUpdate(object));
                insert.executeUpdate();
                RmBd.cerrar();
                return true;
            }
        }catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getIdEquipo(){
        int id = 0;
        String sql = "SELECT eq_id  FROM equipo WHERE eq_nombre = '"+GV.equipo()+"'";
        PreparedStatement consulta;
        try {
            consulta = RmBd.obtener().prepareStatement(sql);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                id = datos.getInt("eq_id");
            }
            RmBd.cerrar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    @Override
    public int getMaxId(Object type) {
        Log.setLog(className, Log.getReg());
        int id = 0;
        try{
            String sql = "";
            if(type instanceof Ficha){
                sql = "SELECT COUNT(fch_id) as id FROM ficha WHERE fch_id LIKE '%/"+getIdEquipo()+"'";
            }
            if(type instanceof Armazon){
                sql = "SELECT COUNT(arm_id) as id FROM armazon WHERE arm_id LIKE '%/"+getIdEquipo()+"'";
            }
            if(type instanceof Cristal){
                sql = "SELECT MAX(cri_id) as id FROM cristal";
            }
            if(type instanceof Convenio){
                sql = "SELECT MAX(cnv_id) as id FROM convenio";
            }
            if(type instanceof Descuento){
                sql = "SELECT MAX(des_id) as id FROM descuento";
            }
            if(type instanceof Despacho){
                sql = "SELECT COUNT(dsp_id) as id FROM despacho WHERE dsp_id LIKE '%/"+getIdEquipo()+"'";
            }
            if(type instanceof Equipo){
                sql = "SELECT MAX(eq_id) as id FROM equipo";
            }
             if(type instanceof HistorialPago){
                sql = "SELECT COUNT(hp_id) as id FROM historial_pago WHERE hp_id LIKE '%/"+getIdEquipo()+"'";
            }
            if(type instanceof Institucion){
                sql = "SELECT MAX(ins_id) as id FROM institucion";
            }
            if(type instanceof InternMail){
                sql = "SELECT MAX(msg_id) as id FROM message";
            }
            if(type instanceof Inventario){
                sql = "SELECT MAX(inv_id) as id FROM inventario";
            }
            if (type instanceof Oficina) {
                sql = "SELECT MAX(of_id) as id FROM oficina";
            }
            if (type instanceof RegistroBaja) {
                sql = "SELECT COUNT(rb_id) as id FROM registri_baja WHERE rb_id LIKE '%/"+getIdEquipo()+"'";
            }
            if (type instanceof TipoPago) {
                sql = "SELECT MAX(tp_id) as id FROM tipo_pago";
            }
            if(type instanceof User){
                sql = "SELECT MAX(us_id) as id FROM usuario";
            }
            if(sql.length()>2){
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    id = datos.getInt("id");
                }
                RmBd.cerrar();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;
    }
    /**
     * Para listar las fichas de debe castear a la clase ResF
     * @param idParam listar fichas = idFicha: enviar original, 
     * id_user: GV.convertFichaIdParamToUser(idParam),
     * rut_cliente: GV.convertFichaIdParamToClient(idParam)
     * @param type listar fichas= de tipo ResF para obtener fichas
     * @return lista de objetos ResF para varios elementos y Ficha para un solo elemento
     */
    @Override
    public ArrayList<Object> listar(String idParam, Object type) {
        //Falta ordenar y agregar clases
        Log.setLog(className, Log.getReg());
        ArrayList<Object> lista = new ArrayList<>();
        idParam = idParam.trim();
        try {
            if(type instanceof Ficha){
                String id = GV.getStr(idParam);
                if(!GV.fichaIdParamIsIdFicha(idParam)){
                    return listar(idParam,new ResF());
                }
                String sql = "SELECT * FROM ficha WHERE fch_id='" + idParam + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    Cliente cliente = (Cliente)getElement(datos.getString("cliente_cli_rut"), 0, new Cliente());
                    Doctor doctor = (Doctor)getElement(datos.getString("doctor_doc_rut"), 0, new Doctor());
                    Institucion institucion = (Institucion)getElement(null, datos.getInt("institucion_ins_id"), new Institucion());
                    Despacho despacho = (Despacho)getElement(datos.getString("fch_id"), 0, new Despacho());
                    User user = (User)getElement(null, datos.getInt("usuario_us_id"), new User());
                    Convenio convenio = (Convenio)getElement(null, datos.getInt("convenio_cnv_id"), new Convenio());
                    Armazon cerca = (Armazon)getElement(datos.getString("fch_id"), 0, new Armazon());
                    Armazon lejos = (Armazon)getElement(datos.getString("fch_id"), 1, new Armazon());
                    lista.add(new Ficha(
                        datos.getString("fch_id"),
                        datos.getDate("fch_fecha"),
                        datos.getDate("fch_fecha_entrega"),
                        datos.getString("fch_lugar_entrega"),
                        datos.getString("fch_hora_entrega"),
                        datos.getString("fch_obs"),
                        datos.getInt("fch_valor_total"),
                        datos.getInt("fch_descuento"),
                        datos.getInt("fch_saldo"),
                        cliente,
                        doctor,
                        institucion,
                        despacho,
                        lejos,
                        cerca,
                        user,
                        convenio,
                        datos.getInt("fch_estado"),
                        datos.getDate("fch_last_update"),
                        datos.getInt("fch_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof ResF){
                String sql = "SELECT * FROM ficha";
                if(!GV.fichaIdParamIsIdFicha(idParam)){
                    sql = (GV.fichaIdParamIsClient(idParam)) ? "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE cliente_cli_rut='" + GV.cleanIdParam(idParam) + "'":sql;
                    sql = (GV.fichaIdParamIsUser(idParam)) ? "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE usuario_us_id=" + GV.cleanIdParam(idParam) + "":sql;
                    sql = (GV.fichaIdParamIsTableList(idParam)) ? "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE fch_estado=" + GV.cleanIdParam(idParam) + "":sql;
                    sql = (GV.fichaIdParamIsTableList(idParam) && GV.strToNumber(idParam)<0)? "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE fch_estado <= 0":sql;
                    sql = (GV.fichaIdParamIsTableList(idParam) && GV.strToNumber(idParam)==0)? "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE fch_estado > 0":sql;
                    //return lista de ResF
                }else{
                    return listar(idParam, new Ficha());//retornara una lista de Fichas
                }
                if (idParam.equals("0")) {
                    sql = "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE fch_estado = 1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha WHERE fch_estado = 0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT fch_id,fch_fecha,fch_valor_total,fch_descuento, fch_saldo,fch_estado,"
                    + "(SELECT cli_nombre FROM cliente WHERE cli_rut = cliente_cli_rut) as cliente,"
                    + "(SELECT cli_comuna FROM cliente WHERE cli_rut = cliente_cli_rut) as comuna,"
                    + "(SELECT cli_ciudad FROM cliente WHERE cli_rut = cliente_cli_rut) as ciudad,"
                    + "(SELECT us_nombre FROM usuario WHERE us_id = usuario_us_id) as vendedor "
                    + "FROM ficha";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new ResF(
                        datos.getString("fch_id"),
                        datos.getDate("fch_fecha"),
                        datos.getInt("fch_valor_total"),
                        datos.getInt("fch_descuento"),
                        datos.getInt("fch_saldo"),
                        datos.getString("cliente"),
                        datos.getString("comuna"),
                        datos.getString("ciudad"),
                        datos.getString("vendedor"),
                        datos.getInt("fch_estado")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Armazon){
                String id = GV.getStr(idParam);
                String subId = "";
                if(!id.isEmpty() && id.contains("<") && id.substring(id.indexOf("<")+1).contains(">")){
                    subId = id.substring(id.indexOf("<")+1,id.indexOf(">")).trim();
                    id=id.substring(0, id.indexOf("<")).trim();
                }
                String sql = "SELECT * FROM armazon WHERE ficha_fch_id='" + id + "' AND arm_tipo="+subId;
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM armazon WHERE arm_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM armazon WHERE arm_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM armazon";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Armazon(
                        datos.getString("arm_id"),
                        datos.getInt("arm_tipo"),
                        datos.getString("arm_marca"),
                        datos.getString("arm_cristal"),
                        datos.getString("arm_add"),
                        datos.getString("arm_od_a"),
                        datos.getString("arm_od_esf"),
                        datos.getString("arm_od_cil"),
                        datos.getString("arm_oi_a"),
                        datos.getString("arm_oi_esf"),
                        datos.getString("arm_oi_cil"),
                        datos.getInt("arm_dp"),
                        datos.getInt("arm_endurecido"),
                        datos.getInt("arm_capa"),
                        datos.getInt("arm_plus_max"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("arm_estado"),
                        datos.getDate("arm_last_update"),
                        datos.getInt("arm_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Cliente){
                String sql = "SELECT * FROM cliente WHERE cli_rut='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM cliente WHERE cli_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM cliente WHERE cli_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM cliente";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cliente(
                        datos.getString("cli_rut"),
                        datos.getString("cli_nombre"),
                        datos.getString("cli_telefono1"),
                        datos.getString("cli_telefono2"),
                        datos.getString("cli_email"),
                        datos.getString("cli_direccion"),
                        datos.getString("cli_comuna"),
                        datos.getString("cli_ciudad"),
                        datos.getInt("cli_sexo"),
                        datos.getDate("cli_nacimiento"),
                        datos.getInt("cli_estado"),
                        datos.getDate("cli_last_update"),
                        datos.getInt("cli_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Convenio){
                String sql = "SELECT * FROM convenio WHERE cnv_id=" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM convenio WHERE cnv_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM convenio WHERE cnv_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM convenio";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Convenio(
                        datos.getInt("cnv_id"),
                        datos.getString("cnv_nombre"),
                        datos.getDate("cnv_fecha_inicio"),
                        datos.getDate("cnv_fecha_fin"),
                        datos.getInt("cnv_cuotas"),
                        datos.getInt("cnv_monto_maximo"),
                        datos.getInt("cnv_monto_pp"),
                        datos.getInt("cnv_maximo_clientes"),
                        datos.getInt("descuento_des_id"),
                        datos.getInt("institucion_ins_id"),
                        datos.getInt("cnv_estado"),
                        datos.getDate("cnv_last_update"),
                        datos.getInt("cnv_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Cristal){
                String sql = "SELECT * FROM cristal WHERE cri_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM cristal WHERE cri_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM cristal WHERE cri_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM cristal";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cristal(
                        datos.getInt("cri_id"),
                        datos.getString("cri_nombre"),
                        datos.getInt("cri_precio"),
                        datos.getInt("cri_estado"),
                        datos.getDate("cri_last_update"),
                        datos.getInt("cri_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Descuento){
                String sql = "SELECT * FROM descuento WHERE des_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM descuento WHERE des_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM descuento WHERE des_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM descuento";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Descuento(
                        datos.getInt("des_id"),
                        datos.getString("des_nombre"),
                        datos.getString("des_descripcion"),
                        datos.getInt("des_porc"),
                        datos.getInt("des_monto"),
                        datos.getInt("des_estado"),
                        datos.getDate("des_last_update"),
                        datos.getInt("des_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Despacho){
                String sql = "SELECT * FROM despacho WHERE ficha_fch_id='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM despacho WHERE dsp_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM despacho WHERE dsp_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM despacho";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Despacho(
                        datos.getString("dsp_id"),
                        datos.getString("dsp_rut"),
                        datos.getString("dsp_nombre"),
                        datos.getDate("dsp_fecha"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("dsp_estado"),
                        datos.getDate("dsp_last_update"),
                        datos.getInt("dsp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Doctor){
                String sql = "SELECT * FROM doctor WHERE doc_rut='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM doctor WHERE doc_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM doctor WHERE doc_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM doctor";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Doctor(
                        datos.getString("doc_rut"),
                        datos.getString("doc_nombre"),
                        datos.getString("doc_telefono"),
                        datos.getString("doc_mail"),
                        datos.getInt("doc_estado"),
                        datos.getDate("doc_last_update"),
                        datos.getInt("doc_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Equipo){
                
                String sql = "SELECT * FROM equipo WHERE eq_id =" + idParam + "";
                if(!GV.isNumeric(idParam)){
                    sql = "SELECT * FROM equipo WHERE eq_nombre ='" + idParam + "'";
                }
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM equipo WHERE eq_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM equipo WHERE eq_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM equipo";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Equipo(
                        datos.getInt("eq_id"),
                        datos.getString("eq_nombre"),
                        datos.getString("eq_licencia"),
                        datos.getInt("eq_estado"),
                        datos.getDate("eq_last_update"),
                        datos.getInt("eq_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof HistorialPago){
                String idFicha = null;
                if(!idParam.isEmpty() && idParam.contains("<") && idParam.substring(idParam.indexOf("<")+1).contains(">")){
                    idFicha = idParam.substring(idParam.indexOf("<")+1,idParam.indexOf(">")).trim();
                }
                String sql = "SELECT * FROM historial_pago WHERE hp_id ='" + idParam + "'";
                if(idFicha != null && GV.getStr(idFicha).isEmpty()){
                    idFicha = null;
                    idParam = "0";
                }
                sql = (idFicha != null) ? "SELECT * FROM historial_pago WHERE ficha_fch_id ='" + idFicha + "'":sql;
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM historial_pago WHERE hp_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM historial_pago WHERE hp_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM historial_pago";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new HistorialPago(
                        datos.getString("hp_id"),
                        datos.getDate("hp_fecha"),
                        datos.getInt("hp_abono"),
                        datos.getInt("tipo_pago_tp_id"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("hp_estado"),
                        datos.getDate("hp_last_update"),
                        datos.getInt("hp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Institucion){
                String sql = "SELECT * FROM institucion WHERE ins_id =" + idParam + "";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM institucion WHERE ins_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM institucion WHERE ins_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM institucion";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Institucion(
                        datos.getInt("ins_id"),
                        datos.getString("ins_nombre"),
                        datos.getString("ins_telefono"),
                        datos.getString("ins_mail"),
                        datos.getString("ins_web"),
                        datos.getString("ins_direccion"),
                        datos.getString("ins_comuna"),
                        datos.getString("ins_ciudad"),
                        datos.getInt("ins_estado"),
                        datos.getDate("ins_last_update"),
                        datos.getInt("ins_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof InternMail){
                String sql = "SELECT * FROM message WHERE msg_id =" + idParam + "";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    User rem = (User)getElement(null, datos.getInt("us_id_remitente"), new User());
                    User des = (User)getElement(null, datos.getInt("us_id_destinatario"), new User());
                    lista.add(new InternMail(
                        datos.getInt("msg_id"),
                        rem,
                        des,
                        datos.getString("msg_asunto"),
                        datos.getString("msg_content"),
                        datos.getDate("msg_fecha"),
                        datos.getString("msg_hora"),
                        datos.getInt("msg_estado"),
                        datos.getDate("msg_last_update"),
                        datos.getInt("msg_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Inventario){
                String sql = "SELECT * FROM inventario WHERE inv_nombre = '" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM inventario WHERE inv_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM inventario WHERE inv_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM inventario";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Inventario(
                        datos.getInt("inv_id"),
                        datos.getString("inv_nombre"),
                        datos.getString("inv_descripcion"),
                        datos.getInt("inv_estado"),
                        datos.getDate("inv_last_update"),
                        datos.getInt("inv_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Lente){
                String sql = "SELECT * FROM lente WHERE len_id ='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM lente WHERE len_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM lente WHERE len_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM lente";
                }
                if (idParam.equals("st")) {
                    sql = "SELECT * FROM lente WHERE len_estado=1 AND len_stock > 0";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Lente(
                        datos.getString("len_id"),
                        datos.getString("len_color"),
                        datos.getString("len_tipo"),
                        datos.getString("len_marca"),
                        datos.getString("len_material"),
                        datos.getInt("len_flex"),
                        datos.getInt("len_clasificacion"),
                        datos.getString("len_descripcion"),
                        datos.getInt("len_precio_ref"),
                        datos.getInt("len_precio_act"),
                        datos.getInt("len_stock"),
                        datos.getInt("len_stock_min"),
                        datos.getString("inventario_inv_id"),
                        datos.getInt("len_estado"),
                        datos.getDate("len_last_update"),
                        datos.getInt("len_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Oficina) {
                String sql = "SELECT * FROM oficina WHERE of_nombre='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM oficina WHERE of_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM oficina WHERE of_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM oficina";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Oficina(
                            datos.getInt("of_id"),
                             datos.getString("of_nombre"),
                             datos.getString("of_direccion"),
                             datos.getString("of_ciudad"),
                             datos.getString("of_telefono1"),
                             datos.getString("of_telefono2"),
                             datos.getString("of_email"),
                             datos.getString("of_web"),
                             datos.getInt("of_estado"),
                             datos.getDate("of_last_update"),
                             datos.getInt("of_last_hour")
                    )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof RegistroBaja) {
                String sql = "SELECT * FROM registro_bajas WHERE rb_id='" + idParam + "'";
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM registro_bajas WHERE rb_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM registro_bajas WHERE rb_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM registro_bajas";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new RegistroBaja(
                            datos.getString("rb_id"),
                            datos.getDate("rb_fecha"),
                            datos.getString("lente_len_id"),
                            datos.getInt("rb_cantidad"),
                            datos.getString("rb_obs"),
                            datos.getInt("rb_estado"),
                            datos.getDate("rb_last_update"),
                            datos.getInt("rb_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof TipoPago) {
                String sql = "SELECT * FROM tipo_pago WHERE tp_nombre='" + idParam + "'";
                sql = (GV.isNumeric(idParam))? "SELECT * FROM tipo_pago WHERE tp_id=" + idParam:sql;
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM tipo_pago WHERE tp_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM tipo_pago WHERE tp_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM tipo_pago";
                }

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new TipoPago(
                            datos.getInt("tp_id"),
                            datos.getString("tp_nombre"),
                            datos.getInt("tp_estado"),
                            datos.getDate("tp_last_update"),
                            datos.getInt("tp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof User){
                String sql = "SELECT * FROM usuario WHERE us_username='" + idParam + "'";
                if(!idParam.equals("0") && isNumber(idParam)){
                    sql = "SELECT * FROM usuario WHERE us_id=" + idParam + "";
                }
                if (idParam.equals("0")) {
                    sql = "SELECT * FROM usuario WHERE us_estado=1";
                }
                if (idParam.equals("-1")) {
                    sql = "SELECT * FROM usuario WHERE us_estado=0";
                }
                if (idParam.equals("-2")) {
                    sql = "SELECT * FROM usuario";
                }
                
                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new User(
                        datos.getInt("us_id"),
                        datos.getString("us_nombre"),
                        datos.getString("us_username"),
                        datos.getString("us_email"),
                        datos.getString("us_pass"),
                        datos.getInt("us_tipo"),
                        datos.getInt("us_estado"),
                        datos.getDate("us_last_update"),
                        datos.getInt("us_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    /**
     * Retorna una lista de objetos comparndo por la ultima fecha de actualización con
     * paramDate y tipo de objeto con type
     * @param paramDate
     * @param type
     * @return 
     */
    @Override
    public ArrayList<Object> listar(Date paramDate, Object type) {
        Log.setLog(className, Log.getReg());
        java.sql.Date param = new java.sql.Date(paramDate.getTime());
        ArrayList<Object> lista = new ArrayList<>();
        try {
            if(type instanceof Ficha){
                String sql = "SELECT * FROM ficha WHERE fch_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new EtiquetFicha(
                        datos.getString("fch_id"),
                        datos.getDate("	fch_fecha"),
                        datos.getDate("fch_fecha_entrega"),
                        datos.getString("fch_lugar_entrega"),
                        datos.getString("fch_hora_entrega"),
                        datos.getString("fch_obs"),
                        datos.getInt("fch_valor_total"),
                        datos.getInt("fch_descuento"),
                        datos.getInt("fch_saldo"),
                        datos.getString("cliente_cli_rut"),
                        datos.getString("doctor_doc_rut"),
                        datos.getInt("institucion_ins_id"),
                        datos.getString("despacho_dsp_id"),
                        datos.getInt("usuario_us_id"),
                        datos.getInt("convenio_cnv_id"),
                        datos.getInt("fch_estado"),
                        datos.getDate("fch_last_update"),
                        datos.getInt("fch_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Armazon){
                String sql = "SELECT * FROM armazon WHERE arm_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Armazon(
                        datos.getString("arm_id"),
                        datos.getInt("arm_tipo"),
                        datos.getString("arm_marca"),
                        datos.getString("arm_cristal"),
                        datos.getString("arm_add"),
                        datos.getString("arm_od_a"),
                        datos.getString("arm_od_esf"),
                        datos.getString("arm_od_cil"),
                        datos.getString("arm_oi_a"),
                        datos.getString("arm_oi_esf"),
                        datos.getString("arm_oi_cil"),
                        datos.getInt("arm_dp"),
                        datos.getInt("arm_endurecido"),
                        datos.getInt("arm_capa"),
                        datos.getInt("arm_plus_max"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("arm_estado"),
                        datos.getDate("arm_last_update"),
                        datos.getInt("arm_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Cliente){
                String sql = "SELECT * FROM cliente WHERE cli_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cliente(
                        datos.getString("cli_rut"),
                        datos.getString("cli_nombre"),
                        datos.getString("cli_telefono1"),
                        datos.getString("cli_telefono2"),
                        datos.getString("cli_email"),
                        datos.getString("cli_direccion"),
                        datos.getString("cli_comuna"),
                        datos.getString("cli_ciudad"),
                        datos.getInt("cli_sexo"),
                        datos.getDate("cli_nacimiento"),
                        datos.getInt("cli_estado"),
                        datos.getDate("cli_last_update"),
                        datos.getInt("cli_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Convenio){
                String sql = "SELECT * FROM convenio WHERE cnv_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Convenio(
                        datos.getInt("cnv_id"),
                        datos.getString("cnv_nombre"),
                        datos.getDate("cnv_fecha_inicio"),
                        datos.getDate("cnv_fecha_fin"),
                        datos.getInt("cnv_cuotas"),
                        datos.getInt("cnv_monto_maximo"),
                        datos.getInt("cnv_monto_pp"),
                        datos.getInt("cnv_maximo_clientes"),
                        datos.getInt("descuento_des_id"),
                        datos.getInt("institucion_ins_id"),
                        datos.getInt("cnv_estado"),
                        datos.getDate("cnv_last_update"),
                        datos.getInt("cnv_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Cristal){
                String sql = "SELECT * FROM cristal WHERE cri_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Cristal(
                        datos.getInt("cri_id"),
                        datos.getString("cri_nombre"),
                        datos.getInt("cri_precio"),
                        datos.getInt("cri_estado"),
                        datos.getDate("cri_last_update"),
                        datos.getInt("cri_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Descuento){
                String sql = "SELECT * FROM descuento WHERE des_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Descuento(
                        datos.getInt("des_id"),
                        datos.getString("des_nombre"),
                        datos.getString("des_descripcion"),
                        datos.getInt("des_porc"),
                        datos.getInt("des_monto"),
                        datos.getInt("des_estado"),
                        datos.getDate("des_last_update"),
                        datos.getInt("des_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Despacho){
                String sql = "SELECT * FROM despacho WHERE dspde_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Despacho(
                        datos.getString("dsp_id"),
                        datos.getString("dsp_rut"),
                        datos.getString("dsp_nombre"),
                        datos.getDate("dsp_fecha"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("dsp_estado"),
                        datos.getDate("dsp_last_update"),
                        datos.getInt("dsp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Doctor){
                String sql = "SELECT * FROM doctor WHERE doc_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Doctor(
                        datos.getString("doc_rut"),
                        datos.getString("doc_nombre"),
                        datos.getString("doc_telefono"),
                        datos.getString("doc_mail"),
                        datos.getInt("doc_estado"),
                        datos.getDate("doc_last_update"),
                        datos.getInt("doc_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Equipo){
                String sql = "SELECT * FROM equipo WHERE eq_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Equipo(
                        datos.getInt("eq_id"),
                        datos.getString("eq_nombre"),
                        datos.getString("eq_licencia"),
                        datos.getInt("eq_estado"),
                        datos.getDate("eq_last_update"),
                        datos.getInt("eq_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof HistorialPago){
                String sql = "SELECT * FROM historial_pago WHERE hp_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new HistorialPago(
                        datos.getString("hp_id"),
                        datos.getDate("hp_fecha"),
                        datos.getInt("hp_abono"),
                        datos.getInt("tipo_pago_tp_id"),
                        datos.getString("ficha_fch_id"),
                        datos.getInt("hp_estado"),
                        datos.getDate("hp_last_update"),
                        datos.getInt("hp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof InternMail){
                String sql = "SELECT * FROM message WHERE msg_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    User rem = (User)getElement(null, datos.getInt("us_id_remitente"), new User());
                    User des = (User)getElement(null, datos.getInt("us_id_destinatario"), new User());
                    lista.add(new InternMail(
                        datos.getInt("msg_id"),
                        rem,
                        des,
                        datos.getString("msg_asunto"),
                        datos.getString("msg_content"),
                        datos.getDate("msg_fecha"),
                        datos.getString("msg_hora"),
                        datos.getInt("msg_estado"),
                        datos.getDate("msg_last_update"),
                        datos.getInt("msg_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Institucion){
                String sql = "SELECT * FROM institucion WHERE ins_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Institucion(
                        datos.getInt("ins_id"),
                        datos.getString("ins_nombre"),
                        datos.getString("ins_telefono"),
                        datos.getString("ins_mail"),
                        datos.getString("ins_web"),
                        datos.getString("ins_direccion"),
                        datos.getString("ins_comuna"),
                        datos.getString("ins_ciudad"),
                        datos.getInt("ins_estado"),
                        datos.getDate("ins_last_update"),
                        datos.getInt("ins_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Inventario){
                String sql = "SELECT * FROM inventario WHERE inv_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Inventario(
                        datos.getInt("inv_id"),
                        datos.getString("inv_nombre"),
                        datos.getString("inv_descripcion"),
                        datos.getInt("inv_estado"),
                        datos.getDate("inv_last_update"),
                        datos.getInt("inv_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof Lente){
                String sql = "SELECT * FROM lente WHERE len_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Lente(
                        datos.getString("len_id"),
                        datos.getString("len_color"),
                        datos.getString("len_tipo"),
                        datos.getString("len_marca"),
                        datos.getString("len_material"),
                        datos.getInt("len_flex"),
                        datos.getInt("len_clasificacion"),
                        datos.getString("len_descripcion"),
                        datos.getInt("len_precio_ref"),
                        datos.getInt("len_precio_act"),
                        datos.getInt("len_stock"),
                        datos.getInt("len_stock_min"),
                        datos.getString("inventario_inv_id"),
                        datos.getInt("len_estado"),
                        datos.getDate("len_last_update"),
                        datos.getInt("len_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof Oficina) {
                String sql = "SELECT * FROM oficina WHERE of_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new Oficina(
                        datos.getInt("of_id"),
                        datos.getString("of_nombre"),
                        datos.getString("of_direccion"),
                        datos.getString("of_ciudad"),
                        datos.getString("of_telefono1"),
                        datos.getString("of_telefono2"),
                        datos.getString("of_email"),
                        datos.getString("of_web"),
                        datos.getInt("of_estado"),
                        datos.getDate("of_last_update"),
                        datos.getInt("of_last_hour")
                    )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof RegistroBaja) {
                String sql = "SELECT * FROM registro_bajas WHERE rb_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new RegistroBaja(
                            datos.getString("rb_id"),
                            datos.getDate("rb_fecha"),
                            datos.getString("lente_len_id"),
                            datos.getInt("rb_cantidad"),
                            datos.getString("rb_obs"),
                            datos.getInt("rb_estado"),
                            datos.getDate("rb_last_update"),
                            datos.getInt("rb_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if (type instanceof TipoPago) {
                String sql = "SELECT * FROM tipo_pago WHERE tp_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new TipoPago(
                            datos.getInt("tp_id"),
                            datos.getString("tp_nombre"),
                            datos.getInt("tp_estado"),
                            datos.getDate("tp_last_update"),
                            datos.getInt("tp_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
            if(type instanceof User){
                String sql = "SELECT * FROM usuario WHERE us_last_update >='" + param + "'";

                PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    lista.add(new User(
                        datos.getInt("us_id"),
                        datos.getString("us_nombre"),
                        datos.getString("us_username"),
                        datos.getString("us_email"),
                        datos.getString("us_pass"),
                        datos.getInt("us_tipo"),
                        datos.getInt("us_estado"),
                        datos.getDate("us_last_update"),
                        datos.getInt("us_last_hour")
                        )
                    );
                }
                RmBd.cerrar();
                return lista;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    /**
     * @param cod 
     * Armazon cod=idFicha, id=tipo de armazon
     * Cliente=>rut,
     * Cristal=>nombre,
     * Descuento=>nombre,
     * Doctor=>rut,
     * Inventario=>nombre,
     * Lente=>cod,
     * Oficina=>nombre,
     * RegistroBaja=>cod,
     * User=>username
     * @param id
     * Institucion=>id,
     * TipoPago=>id
     * @param type
     * Tipo de clase que se desea retornar
     * @return Retorna la clase de tipo Object, luego sólo se debe parsear.
     */
    @Override
    public Object getElement(String cod,int id, Object type) {
        Log.setLog(className, Log.getReg());
        if(cod == null && id == 0){
            return null;
        }
        try{
            if(type instanceof Ficha){
                for (Object object : listar(cod, type)) {//id debe ser el rut del cliente
                    if (((Ficha) object).getCod().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Armazon){
                String idFicha = GV.getStr(cod);
                int tipo = id;
                Armazon armazon = null;
                int auditoria =0;
                for (Object object : listar(GV.getStr(idFicha+"<"+tipo+">"), type)) {//id debe ser el rut del cliente
                    if(((Armazon) object).getIdFicha().equals(idFicha) && ((Armazon)object).getTipo() == tipo){
                        armazon= (Armazon)object;
                        auditoria++;
                    }
                }
                if(auditoria > 1){
                    OptionPane.showMsg("Es necesario corregir algunos datos", "La base de datos tiene conflicto con algunos items.\n"
                            + "Debe informar de este error a su proveedor de software con el siguiente id de seguimiento\n"
                            + "Identificador: ARM_FCH_ID_"+armazon.getIdFicha()+"\nDetalle: El sistema solo debe admitir dos armazones por ficha.", 3);
                }
                return armazon;
            }
            if(type instanceof Cliente){
                for (Object object : listar(cod, type)) {//id debe ser el rut del cliente
                    if (((Cliente) object).getCod().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Convenio){
                for (Object object : listar(""+id, type)) {//id debe ser el rut del cliente
                    if (((Convenio) object).getId() == id ) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Cristal){
                for (Object object : listar(cod, type)) {//id debe ser el nombre del cristal
                    if (((Cristal) object).getNombre().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Descuento){
                for (Object object : listar(cod, type)) {//id debe ser el nombre del descuento
                    if (GV.strCompare(((Descuento) object).getNombre(),cod)) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Despacho){
                int auditoria = 0;
                Despacho despacho = null;
                for (Object object : listar(cod, type)) {//id debe ser el nombre del descuento
                    if (GV.strCompare(((Despacho) object).getIdFicha(),cod)) {
                        despacho = (Despacho)object;
                        auditoria++;
                    }
                }
                if(auditoria > 1){
                    OptionPane.showMsg("Es necesario corregir algunos datos", "La base de datos tiene conflicto con algunos items.\n"
                            + "Debe informar de este error a su proveedor de software con el siguiente id de seguimiento\n"
                            + "Identificador: DSP_FCH_ID_"+despacho.getIdFicha()+"\nDetalle: El sistema solo debe admitir un despacho por ficha.", 3);
                }
                return despacho;
            }
            if(type instanceof Doctor){
                for (Object object : listar(cod, type)) {//id debe ser el rut del doctor
                    if(GV.containIntegrs(cod)){//comparar por el rut
                        if (((Doctor) object).getCod().equals(cod)) {
                            return object;
                        }
                    }else{//comparar por el nombre
                        if (((Doctor) object).getNombre().equals(cod)) {
                            return object;
                        }
                    }  
                }
                return null;
            }
            if(type instanceof Equipo){
                if(cod != null){
                    for (Object object : listar(cod, type)) {//id debe ser el rut del doctor
                        if (((Equipo) object).getNombre().equals(cod)) {
                            return object;
                        }
                    }
                }else{
                    for (Object object : listar(""+id, type)) {//id debe ser el rut del doctor
                        if (((Equipo) object).getId() == id) {
                            return object;
                        }
                    }
                }
                return null;
            }
            if(type instanceof HistorialPago){
                for (Object object : listar(cod, type)) {//id debe ser el id de la institucion
                    if (((HistorialPago) object).getCod().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Institucion){
                for (Object object : listar(""+id, type)) {//id debe ser el id de la institucion
                    if (((Institucion) object).getId() == id) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof InternMail){
                for (Object object : listar(""+id, type)) {//id debe ser el id de la institucion
                    if (((InternMail) object).getId() == id) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Inventario){
                for (Object object : listar(cod, type)) {//id debe ser el rut del doctor
                    if (((Inventario) object).getNombre().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if(type instanceof Lente){
                for (Object object : listar(cod, type)) {//id debe ser el id de la institucion
                    if (((Lente) object).getCod().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if (type instanceof Oficina) {
                for (Object object : listar(cod, type)) {//id debe ser el id de la ficha
                    if (((Oficina) object).getNombre().toLowerCase().equals(cod.toLowerCase())) {
                        return object;
                    }
                }
                return null;
            }
            if (type instanceof RegistroBaja) {
                for (Object object : listar(cod, type)) {//id debe ser el id de la ficha
                    if (((RegistroBaja) object).getCod().equals(cod)) {
                        return object;
                    }
                }
                return null;
            }
            if (type instanceof TipoPago) {
                if(cod == null){
                    for (Object object : listar(""+id, type)) {//cod es el nombre de la entidad
                        if (((TipoPago) object).getId() == id) {
                            return object;
                        }
                    }
                }else{
                    for (Object object : listar(cod, type)) {//cod es el nombre de la entidad
                        if (((TipoPago) object).getNombre().trim().toLowerCase().equals(cod.toLowerCase())) {
                            return object;
                        }
                    }
                }   
                return null;
            }
            if(type instanceof User){
                if(cod == null){
                    for (Object object : listar(""+id, type)) {
                        if (((User) object).getId() == id) {
                            return object;
                        }
                    }
                }else{
                    for (Object object : listar(cod, type)) {
                        if (((User) object).getUsername().trim().toLowerCase().equals(cod.toLowerCase())) {
                            return object;
                        }
                    }
                }
                return null;
            }
        }catch(Exception ex){
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }
        OptionPane.showMsg("Instancia no encontrada", "No se encuentra la instancia, se retornará un valor vacío,"
                + "\nUbicación:"+ className+"\nDetalle: "+Log.getLog(), 3);
        return null;
    }
    /**
     * Compara por los siguientes atributos:
     * Cliente=>rut,
     * Cristal=>nombre,
     * Descuento=>nombre,
     * Doctor=>rut,
     * Lente=>cod,
     * Oficina=>nombre,
     * RegistroBaja=>cod,
     * User=>username,
     * Institucion=>id,
     * TipoPago=>nombre
     * @param object
     * @return 
     */
    @Override
    public boolean exist(Object object) {
        Log.setLog(className, Log.getReg());
        if (object instanceof Ficha) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Ficha) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Armazon) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Armazon) object).getIdFicha(),((Armazon)object).getTipo(),object) != null) {
                return true;
            }
        }
        if (object instanceof Cliente) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Cliente) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Convenio) {
            Log.setLog(className, Log.getReg());
            if (getElement(null,((Convenio) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Cristal) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Cristal) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Descuento) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Descuento) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Despacho) {
            Log.setLog(className, Log.getReg());
            if (getElement(((Despacho) object).getIdFicha(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Doctor) {
            if (getElement(((Doctor) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Equipo) {
            if (getElement(null,((Equipo) object).getId(),object) != null) {
                return true;
            }
            if (getElement(((Equipo) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof HistorialPago) {
            if (getElement(((HistorialPago) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Institucion) {
            if (getElement(null,((Institucion) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof InternMail) {
            if (getElement(null,((InternMail) object).getId(),object) != null) {
                return true;
            }
        }
        if (object instanceof Inventario) {
            if (getElement(((Inventario) object).getNombre(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Lente) {
            if (getElement(((Lente) object).getCod(),0,object) != null) {
                return true;
            }
        }
        if (object instanceof Oficina) {
            if (getElement(((Oficina) object).getNombre(),0, object) != null) {
                return true;
            }
        }
        if (object instanceof RegistroBaja) {
            if (getElement(((RegistroBaja) object).getCod(),0, object) != null) {
                return true;
            }
        }
        if (object instanceof TipoPago) {
            if (getElement(((TipoPago) object).getNombre(),0, object) != null) {
                return true;
            }
        }
        if (object instanceof User) {
            if (getElement(((User) object).getUsername(),0,object) != null) {
                return true;
            }
        }
        return false;
    }
    private String sqlInsert(Object objectParam){
        if(objectParam instanceof Armazon){
            Armazon object = (Armazon)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO armazon VALUES('"
                    + object.getCod() + "',"
                    + object.getTipo()+ ",'"
                    + object.getMarca()+ "','"
                    + object.getCristal()+ "','"
                    + object.getAdd()+ "','"
                    + object.getOdA()+ "','"
                    + object.getOdEsf()+ "','"
                    + object.getOdCil()+ "','"
                    + object.getOiA()+ "','"
                    + object.getOiEsf()+ "','"
                    + object.getOiCil()+ "',"
                    + object.getDp()+ ","
                    + object.getEndurecido()+ ","
                    + object.getCapa()+ ","
                    + object.getPlusMax()+ ",'"
                    + object.getIdFicha()+ "',"
                    + object.getEstado() + ",'"
                    + sqlfecha1 + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Cliente){
            Cliente object = (Cliente)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getNacimiento().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO cliente VALUES('"
                    + object.getCod() + "','"
                    + object.getNombre() + "','"
                    + object.getTelefono1() + "','"
                    + object.getTelefono2() + "','"
                    + object.getEmail() + "','"
                    + object.getDireccion() + "','"
                    + object.getComuna() + "','"
                    + object.getCiudad() + "',"
                    + object.getSexo() + ",'"
                    + sqlfecha1 + "',"
                    + object.getEstado() + ",'"
                    + sqlfecha2 + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Convenio){
            Convenio object = (Convenio)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFechaInicio().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaFin().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha3 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO convenio VALUES("
                    + object.getId()+ ",'"
                    + object.getNombre() + "','"
                    + sqlfecha1+ "','"
                    + sqlfecha2+ "',"
                    + object.getCuotas()+ ","
                    + object.getMontoMaximo()+ ","
                    + object.getMontoPp()+ ","
                    + object.getMaximoClientes()+ ","
                    + object.getIdDescuento()+ ","
                    + object.getIdInstitucion()+ ","
                    + object.getEstado() + ",'"
                    + sqlfecha3 + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Cristal){
            Cristal object = (Cristal)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO cristal VALUES("
                    + object.getId() + ",'"
                    + object.getNombre() + "',"
                    + object.getPrecio() + ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Descuento){
            Descuento object = (Descuento)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO descuento VALUES("
                    + object.getId() + ",'"
                    + object.getNombre() + "','"
                    + object.getDescripcion() + "',"
                    + object.getPorcetange() + ","
                    + object.getMonto() + ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Despacho){
            Despacho object = (Despacho)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO despacho VALUES('"
                    + object.getCod()+ "','"
                    + object.getRut()+ "','"
                    + object.getNombre()+ "','"
                    + sqlfecha1 + "','"
                    + object.getIdFicha()+ "',"
                    + object.getEstado() + ",'"
                    + sqlfecha2 + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof Doctor){
            Doctor object = (Doctor)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO doctor VALUES('"
                            + object.getCod() + "','"
                            + object.getNombre() + "','"
                            + object.getTelefono() + "','"
                            + object.getEmail() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Equipo){
            Equipo object = (Equipo)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO equipo VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getLicencia()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Ficha){
            EtiquetFicha object = new EtiquetFicha((Ficha)objectParam);
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaEntrega().getTime());
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO ficha VALUES('"
                    + object.getCod() + "','"
                    + sqlfecha1+ "','"
                    + sqlfecha2+ "','"
                    + object.getLugarEntrega()+ "','"
                    + object.getHoraEntrega()+ "','"
                    + object.getObservacion()+ "',"
                    + object.getValorTotal()+ ","
                    + object.getDescuento()+ ","
                    + object.getSaldo()+ ",'"
                    + object.getRutCliente()+ "','"
                    + object.getRutDoctor()+ "',"
                    + object.getIdInstitucion()+ ",'"
                    + object.getIdDespacho()+ "',"
                    + object.getIdUser()+ ","
                    + object.getIdConvenio()+ ","
                    + object.getEstado() + ",'"
                    + sqlfecha + "',"
                    + object.getLastHour() + ")";
        }
        if(objectParam instanceof HistorialPago){
            HistorialPago object = (HistorialPago)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO historial_pago VALUES('"
                            + object.getCod()+ "','"
                            + sqlfecha1 + "',"
                            + object.getAbono()+ ","
                            + object.getIdTipoPago()+ ",'"
                            + object.getIdFicha()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha2 + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Institucion){
            Institucion object = (Institucion)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO institucion VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getTelefono() + "','"
                            + object.getEmail() + "','"
                            + object.getWeb()+ "','"
                            + object.getDireccion()+ "','"
                            + object.getComuna()+ "','"
                            + object.getCiudad()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof InternMail){
            InternMail object = (InternMail)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO message VALUES("
                            + object.getId()+ ","
                            + object.getRemitente().getId() + ","
                            + object.getDestinatario().getId()+ ",'"
                            + object.getAsunto()+ "','"
                            + object.getContenido()+ "','"
                            + sqlfecha1+ "','"
                            + object.getHora() + "',"
                            + object.getEstado()+ ",'"
                            + sqlfecha2 + "',"
                            + object.getLastHour()+ ")";
        }
        if(objectParam instanceof Inventario){
            Inventario object = (Inventario)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO inventario VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "','"
                            + object.getDescripcion()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Lente){
            Lente object = (Lente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO lente VALUES('"
                            + object.getCod()+ "','"
                            + object.getColor()+ "','"
                            + object.getTipo()+ "','"
                            + object.getMarca()+ "','"
                            + object.getMaterial()+ "',"
                            + object.getFlex()+ ","
                            + object.getClasificacion()+ ",'"
                            + object.getDescripcion()+ "',"
                            + object.getPrecioRef()+ ","
                            + object.getPrecioAct()+ ","
                            + object.getStock()+ ","
                            + object.getStockMin()+ ",'"
                            + object.getInventario()+ "',"
                            + object.getEstado()+ ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof Oficina){
            Oficina object = (Oficina)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO oficina VALUES("
                            + object.getId() + ",'"
                            + object.getNombre() + "','"
                            + object.getDireccion() + "','"
                            + object.getCiudad() + "','"
                            + object.getTelefono1() + "','"
                            + object.getTelefono2() + "','"
                            + object.getEmail() + "','"
                            + object.getWeb() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof RegistroBaja){
            RegistroBaja object = (RegistroBaja)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO registro_bajas VALUES("
                            + object.getCod()+ ",'"
                            + sqlfecha1 + "','"
                            + object.getIdLente()+ "',"
                            + object.getCantidad()+ ",'"
                            + object.getObs()+ "',"
                            + object.getEstado() + ",'"
                            + sqlfecha2 + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof TipoPago){
            TipoPago object = (TipoPago)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO tipo_pago VALUES("
                            + object.getId()+ ",'"
                            + object.getNombre() + "',"
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        if(objectParam instanceof User){
            User object = (User)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "INSERT INTO usuario VALUES("
                            + object.getId() + ",'"
                            + object.getNombre() + "','"
                            + object.getUsername() + "','"
                            + object.getEmail() + "','"
                            + object.getPass() + "',"
                            + object.getTipo() + ","
                            + object.getEstado() + ",'"
                            + sqlfecha + "',"
                            + object.getLastHour() + ")";
        }
        return null;
    }

    private String sqlUpdate(Object objectParam) {
        if(objectParam instanceof Armazon){
            Armazon object = (Armazon)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE armazon set arm_tipo = " + object.getTipo()
                        + ", arm_marca = '" + object.getMarca()
                        + "', arm_cristal = '" + object.getCristal()
                        + "', arm_add = '" + object.getAdd()
                        + "', arm_od_a = '" + object.getOdA()
                        + "', arm_od_esf = '" + object.getOdEsf()
                        + "', arm_od_cil = '" + object.getOdCil()
                        + "', arm_oi_a = '" + object.getOiA()
                        + "', arm_oi_esf = '" + object.getOiEsf()
                        + "', arm_oi_cil = '" + object.getOiCil()
                        + "', arm_dp = " + object.getDp()
                        + ", arm_endurecido = " + object.getEndurecido()
                        + ", arm_capa = " + object.getCapa()
                        + ", arm_plus_max = " + object.getPlusMax()
                        + ", ficha_fch_id = '" + object.getIdFicha()
                        + "', arm_estado = " + object.getEstado()
                        + ", arm_last_update = '" + sqlfecha1
                        + "', arm_last_hour = " + object.getLastHour()
                        + " WHERE arm_id = '" + object.getCod() 
                        + "' AND ((arm_last_update < '"+sqlfecha1+"')OR"
                        + "(arm_last_update = '"+sqlfecha1+"' AND arm_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Cliente){
            Cliente object = (Cliente)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getNacimiento().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE cliente set cli_nombre = '" + object.getNombre()
                        + "', cli_telefono1 = '" + object.getTelefono1()
                        + "', cli_telefono2 = '" + object.getTelefono2()
                        + "', cli_email = '" + object.getEmail()
                        + "', cli_direccion = '" + object.getDireccion()
                        + "', cli_comuna = '" + object.getComuna()
                        + "', cli_ciudad = '" + object.getCiudad()
                        + "', cli_sexo = " + object.getSexo()
                        + ", cli_nacimiento = '" + sqlfecha1
                        + "', cli_estado = " + object.getEstado()
                        + ", cli_last_update = '" + sqlfecha2
                        + "', cli_last_hour = " + object.getLastHour()
                        + " WHERE cli_rut = '" + object.getCod() 
                        + "' AND ((cli_last_update < '"+sqlfecha2+"')OR"
                        + "(cli_last_update = '"+sqlfecha2+"' AND cli_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Convenio){
            Convenio object = (Convenio)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFechaInicio().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaFin().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha3 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return "UPDATE convenio set cnv_nombre = '" + object.getNombre()
                        + "', cnv_fecha_inicio = '" + sqlfecha1
                        + "', cnv_fecha_fin = '" + sqlfecha2
                        + "', cnv_cuotas = " + object.getCuotas()
                        + ", cnv_monto_maximo = " + object.getMontoMaximo()
                        + ", cnv_monto_pp = " + object.getMontoPp()
                        + ", cnv_maximo_clientes = " + object.getMaximoClientes()
                        + ", descuento_des_id = " + object.getIdDescuento()
                        + ", institucion_ins_id = " + object.getIdInstitucion()
                        + ", cnv_estado = " + object.getEstado()
                        + ", cnv_last_update = '" + sqlfecha3
                        + "', cnv_last_hour = " + object.getLastHour()
                        + " WHERE cnv_id = " + object.getId()
                        + " AND ((cnv_last_update < '"+sqlfecha3+"')OR"
                        + "(cnv_last_update = '"+sqlfecha3+"' AND cnv_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Cristal){
            Cristal object = (Cristal)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE cristal set cri_nombre = '" + object.getNombre()
                        + "', cri_precio = " + object.getPrecio()
                        + ", cri_estado = " + object.getEstado()
                        + ", cri_last_update = '" + sqlfecha
                        + "', cri_last_hour = " + object.getLastHour()
                        + " WHERE cri_id = " + object.getId() 
                        + " AND ((cri_last_update < '"+sqlfecha+"')OR"
                        + "(cri_last_update = '"+sqlfecha+"' AND cri_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Descuento){
            Descuento object = (Descuento)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE descuento set des_nombre = '" + object.getNombre()
                        + "', des_descripcion = '" + object.getDescripcion()
                        + "', des_porc = " + object.getPorcetange()
                        + ", des_monto = " + object.getMonto()
                        + ", des_estado = " + object.getEstado()
                        + ", des_last_update = '" + sqlfecha
                        + "', des_last_hour = " + object.getLastHour()
                        + " WHERE des_id = " + object.getId() 
                        + " AND ((des_last_update < '"+sqlfecha+"')OR"
                        + "(des_last_update = '"+sqlfecha+"' AND des_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Despacho){
            Despacho object = (Despacho)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE despacho set dsp_rut = '" + object.getRut()
                        + "', dsp_nombre = '" + object.getNombre()
                        + "', dsp_fecha = '" + sqlfecha1
                        + "', ficha_fch_id = '" + object.getIdFicha()
                        + "', dsp_estado = " + object.getEstado()
                        + ", dsp_last_update = '" + sqlfecha2
                        + "', dsp_last_hour = " + object.getLastHour()
                        + " WHERE dsp_id = '" + object.getCod()
                        + "' AND ((dsp_last_update < '"+sqlfecha2+"')OR"
                        + "(dsp_last_update = '"+sqlfecha2+"' AND dsp_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Doctor){
            Doctor object = (Doctor)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE doctor set doc_nombre = '" + object.getNombre()
                        + "', doc_telefono = '" + object.getTelefono()
                        + "', doc_mail = '" + object.getEmail()
                        + "', doc_estado = " + object.getEstado()
                        + ", doc_last_update = '" + sqlfecha
                        + "', doc_last_hour = " + object.getLastHour()
                        + " WHERE doc_rut = '" + object.getCod() 
                        + "' AND ((doc_last_update < '"+sqlfecha+"')OR"
                        + "(doc_last_update = '"+sqlfecha+"' AND doc_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Equipo){
            Equipo object = (Equipo)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE equipo set eq_nombre = '" + object.getNombre()
                        + "', eq_licencia = '" + object.getLicencia()
                        + "', eq_estado = " + object.getEstado()
                        + ", eq_last_update = '" + sqlfecha
                        + "', eq_last_hour = " + object.getLastHour()
                        + " WHERE eq_id = " + object.getId()    
                        + " AND ((eq_last_update < '"+sqlfecha+"')OR"
                        + "(eq_last_update = '"+sqlfecha+"' AND eq_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Ficha){
            EtiquetFicha object = new EtiquetFicha((Ficha)objectParam);
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getFechaEntrega().getTime());//la transforma a sql.Date
            return "UPDATE ficha set fch_id = '" + object.getCod()
                        + "', fch_fecha = '" + sqlfecha1
                        + "', fch_fecha_entrega = '" + sqlfecha2
                        + "', fch_lugar_entrega = '" + object.getLugarEntrega()
                        + "', fch_hora_entrega = '" + object.getHoraEntrega()
                        + "', fch_obs = '" + object.getObservacion()
                        + "', fch_valor_total = " + object.getValorTotal()
                        + ", fch_descuento = " + object.getDescuento()
                        + ", fch_saldo = " + object.getSaldo()
                        + ", cliente_cli_rut = '" + object.getRutCliente()
                        + "', doctor_doc_rut = '" + object.getRutDoctor()
                        + "', institucion_ins_id = " + object.getIdInstitucion()
                        + ", despacho_dsp_id = '" + object.getIdDespacho()
                        + "', usuario_us_id = " + object.getIdUser()
                        + ", convenio_cnv_id = " + object.getIdConvenio()
                        + ", fch_estado = " + object.getEstado()
                        + ", fch_last_update = '" + sqlfecha
                        + "', fch_last_hour = " + object.getLastHour()
                        + " WHERE fch_id = '" + object.getCod() 
                        + "' AND ((fch_last_update < '"+sqlfecha+"')OR"
                        + "(fch_last_update = '"+sqlfecha+"' AND fch_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof HistorialPago){
            HistorialPago object = (HistorialPago)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE historial_pago set hp_fecha = '" + sqlfecha1
                        + "', hp_abono = " + object.getAbono()
                        + ", tipo_pago_tp_id = " + object.getIdTipoPago()
                        + ", ficha_fch_id = '"+object.getIdFicha()
                        + "', hp_estado = "+object.getEstado()
                        + ", hp_last_update = '" + sqlfecha2
                        + "', hp_last_hour = " + object.getLastHour()
                        + " WHERE hp_id = " + object.getCod()
                        + " AND ((hp_last_update < '"+sqlfecha2+"')OR"
                        + "(hp_last_update = '"+sqlfecha2+"' AND hp_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Institucion){
            Institucion object = (Institucion)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE institucion set ins_nombre = '" + object.getNombre()
                        + "', ins_telefono = '" + object.getTelefono()
                        + "', ins_mail = '" + object.getEmail()
                        + "', ins_web = '" + object.getWeb()
                        + "', ins_direccion = '" + object.getDireccion()
                        + "', ins_comuna = '" + object.getComuna()
                        + "', ins_ciudad = '" + object.getCiudad()
                        + "', ins_estado = " + object.getEstado()
                        + ", ins_last_update = '" + sqlfecha
                        + "', ins_last_hour = " + object.getLastHour()
                        + " WHERE ins_id = " + object.getId()
                        + " AND ((ins_last_update < '"+sqlfecha+"')OR"
                        + "(ins_last_update = '"+sqlfecha+"' AND ins_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof InternMail){
            InternMail object = (InternMail)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE message set us_id_remitente = " + object.getRemitente().getId()
                        + ", us_id_destinatario = " + object.getDestinatario().getId()
                        + ", msg_asunto = '" + object.getAsunto()
                        + "', msg_content = '" + object.getContenido()
                        + "', msg_fecha = '" + sqlfecha1
                        + "', msg_hora = '" + object.getHora()
                        + "', msg_estado = " + object.getEstado()
                        + ", msg_last_update = '" + sqlfecha2
                        + "', msg_last_hour = " + object.getLastHour()
                        + " WHERE msg_id = " + object.getId()
                        + " AND ((msg_last_update < '"+sqlfecha2+"')OR"
                        + "(msg_last_update = '"+sqlfecha2+"' AND msg_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Inventario){
            Inventario object = (Inventario)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE inventario set inv_nombre = '" + object.getNombre()
                        + "', inv_descripcion = '" + object.getDescripcion()
                        + "', inv_estado = " + object.getEstado()
                        + ", inv_last_update = '" + sqlfecha
                        + "', inv_last_hour = " + object.getLastHour()
                        + " WHERE inv_id = " + object.getId()
                        + " AND ((inv_last_update < '"+sqlfecha+"')OR"
                        + "(inv_last_update = '"+sqlfecha+"' AND inv_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Lente){
            Lente object = (Lente)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE lente set len_color = '" + object.getColor()
                        + "', len_tipo = '" + object.getTipo()
                        + "', len_marca = '" + object.getMarca()
                        + "', len_material = '" + object.getMaterial()
                        + "', len_flex = " + object.getFlex()
                        + ", len_clasificacion = " + object.getClasificacion()
                        + ", len_descripcion = '" + object.getDescripcion()
                        + "', len_precio_ref = " + object.getPrecioRef()
                        + ", len_precio_act = " + object.getPrecioAct()
                        + ", len_stock = " + object.getStock()
                        + ", len_stock_min = " + object.getStockMin()
                        + ", inventario_inv_id = '" + object.getInventario()
                        + "', len_estado = " + object.getEstado()
                        + ", len_last_update = '" + sqlfecha
                        + "', len_last_hour = " + object.getLastHour()
                        + " WHERE len_id = '" + object.getCod()
                        + "' AND ((len_last_update < '"+sqlfecha+"')OR"
                        + "(len_last_update = '"+sqlfecha+"' AND len_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof Oficina){
            Oficina object = (Oficina)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE oficina set of_nombre = '" + object.getNombre()
                        + "', of_direccion = '" + object.getDireccion()
                        + "', of_ciudad = '" + object.getCiudad()
                        + "', of_telefono1 = '" + object.getTelefono1()
                        + "', of_telefono2 = '" + object.getTelefono2()
                        + "', of_email = '" + object.getEmail()
                        + "', of_web = '" + object.getWeb()
                        + "', of_estado = " + object.getEstado()
                        + ", of_last_update = '" + sqlfecha
                        + "', of_last_hour = " + object.getLastHour()
                        + " WHERE of_id = " + object.getId() 
                        + " AND ((of_last_update < '"+sqlfecha+"')OR"
                        + "(of_last_update = '"+sqlfecha+"' AND of_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof RegistroBaja){
            RegistroBaja object = (RegistroBaja)objectParam;
            java.sql.Date sqlfecha1 = new java.sql.Date(object.getFecha().getTime());//la transforma a sql.Date
            java.sql.Date sqlfecha2 = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE registro_bajas set rb_fecha = '" + sqlfecha1
                        + "', lente_len_id = '" + object.getIdLente()
                        + "', rb_cantidad = " + object.getCantidad()
                        + ", rb_obs = '" + object.getObs()
                        + "', rb_estado = " + object.getEstado()
                        + ", rb_last_update = '" + sqlfecha2
                        + "', rb_last_hour = " + object.getLastHour()
                        + " WHERE rb_id = '" + object.getCod()
                        + "' AND ((rb_last_update < '"+sqlfecha2+"')OR"
                        + "(rb_last_update = '"+sqlfecha2+"' AND rb_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof TipoPago){
            TipoPago object = (TipoPago)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE tipo_pago set tp_nombre = '" + object.getNombre()
                        + "', tp_estado = " + object.getEstado()
                        + ", tp_last_update = '" + sqlfecha
                        + "', tp_last_hour = " + object.getLastHour()
                        + " WHERE tp_id = " + object.getId()
                        + " AND ((tp_last_update < '"+sqlfecha+"')OR"
                        + "(tp_last_update = '"+sqlfecha+"' AND tp_last_hour < "+object.getLastHour()+"))";
        }
        if(objectParam instanceof User){
            User object = (User)objectParam;
            java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
            return  "UPDATE usuario set us_nombre = '" + object.getNombre()
                        + "', us_username = '" + object.getUsername()
                        + "', us_email = '" + object.getEmail()
                        + "', us_pass = '" + object.getPass()
                        + "', us_tipo = " + object.getTipo()
                        + ", us_estado = " + object.getEstado()
                        + ", us_last_update = '" + sqlfecha
                        + "', us_last_hour = " + object.getLastHour()
                        + " WHERE us_id = " + object.getId()
                        + " AND ((us_last_update < '"+sqlfecha+"')OR"
                        + "(us_last_update = '"+sqlfecha+"' AND us_last_hour < "+object.getLastHour()+"))";
        }
        return null;
    }
    
    private boolean isNumber(String arg){
        if(arg == null)
            return false;
        arg = arg.replaceAll("[^0-9]", "");
        if(arg.isEmpty())
            return false;
        return true;
    }
    public ArrayList<InternMail> mensajes(int remitente, int destinatario, int estado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ArrayList<InternMail> lista = new ArrayList<>();
        String sql = "";
        if(remitente > 0){
            sql = "SELECT msg_id,us_id_remitente,us_id_destinatario,msg_asunto, msg_content,msg_fecha,msg_hora, msg_estado, msg_last_update, msg_last_hour,"
                    + "(SELECT us_username FROM usuario WHERE us_id = us_id_remitente) as rem,"
                    + "(SELECT us_username FROM usuario WHERE us_id = us_id_destinatario) as des "
                    + "FROM message WHERE us_id_remitente = "+remitente+" AND msg_estado > 0";
            if(estado > 0)
                sql = "SELECT msg_id,us_id_remitente,us_id_destinatario,msg_asunto, msg_content,msg_fecha,msg_hora, msg_estado, msg_last_update, msg_last_hour,"
                        + ",(SELECT us_username FROM usuario WHERE us_id = us_id_remitente) as rem,"
                        + "(SELECT us_username FROM usuario WHERE us_id = us_id_destinatario) as des "
                        + "FROM message WHERE us_id_remitente = " + remitente + " AND msg_estado = "+estado;
        }else{
            sql = "SELECT msg_id,us_id_remitente,us_id_destinatario,msg_asunto, msg_content,msg_fecha,msg_hora, msg_estado, msg_last_update, msg_last_hour,"
                    + "(SELECT us_username FROM usuario WHERE us_id = us_id_remitente) as rem,"
                    + "(SELECT us_username FROM usuario WHERE us_id = us_id_destinatario) as des "
                    + "FROM message WHERE us_id_destinatario = " + destinatario + " AND msg_estado > 0";
            if(estado > 0)
                sql = "SELECT msg_id,us_id_remitente,us_id_destinatario,msg_asunto, msg_content,msg_fecha,msg_hora, msg_estado, msg_last_update, msg_last_hour,"
                        + "(SELECT us_username FROM usuario WHERE us_id = us_id_remitente) as rem,"
                        + "(SELECT us_username FROM usuario WHERE us_id = us_id_destinatario) as des "
                        + "FROM message WHERE us_id_destinatario = " + destinatario + " AND msg_estado = "+estado;
        }
        PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            User rem = (User)getElement(datos.getString("rem"), 0, new User());
            User des = (User)getElement(datos.getString("des"), 0, new User());
            lista.add(new InternMail(
                datos.getInt("msg_id"),
                rem,
                des,
                datos.getString("msg_asunto"),
                datos.getString("msg_content"),
                datos.getDate("msg_fecha"),
                datos.getString("msg_hora"),
                datos.getInt("msg_estado"),
                datos.getDate("msg_last_update"),
                datos.getInt("msg_last_hour")
                )
            );
        }
        RmBd.cerrar();
        return lista;
    }
}
