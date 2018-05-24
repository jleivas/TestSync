/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.user;

import bd.LcBd;
import entities.User;
import fn.OptionPane;
import fn.date.Cmp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sync.SyncBd;

/**
 *
 * @author sdx
 */
public class LcBdUser implements SyncBd{
    
    /**
     * Agrega o modifica un objeto en la base de datos local
     * @param object
     * @return true si se insertó o modificó
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static boolean update(User object) throws   SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if(object != null){
                PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT us_id FROM usuario WHERE us_id="+object.getId());
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    LcBd.cerrar();
                    return modificar(object);
                }    
                //////// dar formato String a fecha
                java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
                
                PreparedStatement insert = LcBd.obtener().prepareStatement(
                        "INSERT INTO usuario VALUES("
                                +object.getId()+",'"
                                +object.getNombre()+"','"
                                +object.getUsername()+"','"
                                +object.getPass()+"',"
                                +object.getTipo()+","
                                +object.getEstado()+",'"
                                +sqlfecha+"')"
                               );
                if(insert.executeUpdate()!=0){
                    LcBd.cerrar();
                    //OptionPane.showMsg("Operación realizada correctamente", "Usuario: "+object.getUsername()+"\nId: "+object.getId()+"\nAgregado correctamente.", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
        }
        OptionPane.showMsg("Error inseperado en la operación", "Usuario: "+object.getUsername()+"\nId: "+object.getId()+"\nNo se pudo insertar a los registros.", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public static boolean modificar(User object) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        PreparedStatement consulta = LcBd.obtener().prepareStatement("SELECT * FROM usuario WHERE us_id="+object.getId());
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            Date dsp_fecha= new Date();
            try {
                dsp_fecha = datos.getDate("us_last_update");
            } catch (Exception e) {
                OptionPane.showMsg("Error al convertir fecha", "sync.object.LcBdUser::modificar(User object):\nSe cayó al intentar convertir la fecha", JOptionPane.ERROR_MESSAGE);
            }
            if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                return false;
            }
        }
        java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
        PreparedStatement insert = LcBd.obtener().prepareStatement(
                "UPDATE usuario set us_nombre = '"+object.getNombre()
                        +"', us_username = '"+object.getUsername()
                        +"', us_pass = '"+object.getPass()
                        +"', us_tipo = "+object.getTipo()
                        +", us_estado = "+object.getEstado()
                        +", us_last_update = '"+sqlfecha
                        +"' WHERE us_id = "+object.getId()+" AND us_last_update <= '"+sqlfecha+"'");
        if(insert.executeUpdate()!=0){
            LcBd.cerrar();
            return true;
        }
        else{
            LcBd.cerrar();
            return true;
        }
    }
    
    public ArrayList<User> listar(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String sql="SELECT * FROM usuario WHERE us_id="+id;
        if(id==0){
        sql="SELECT * FROM usuario WHERE us_estado=1";
        }
         if(id==-1){
        sql="SELECT * FROM usuario WHERE us_estado=0";
        }
         if(id==-2){
        sql="SELECT * FROM usuario";
        }
        
        PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<User> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new User(
                    datos.getInt("us_id")
                    , datos.getString("us_nombre")
                    , datos.getString("us_username")
                    , datos.getString("us_pass")
                    , datos.getInt("us_tipo")
                    , datos.getInt("us_estado")
                    , datos.getDate("us_last_update")
                    )
            );
        }
        LcBd.cerrar();
        return lista;
    }
    
    

    @Override
    public boolean add(Object object) {
        System.out.println("LcBdUser::add(Object object)");
        try {
            return update((User)object);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(LcBdUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Object get(String objectId) {
        try {
            for (User temp : listar(-2)) {
                if(temp.getUsername().toLowerCase().equals(objectId.toLowerCase()))
                    return temp;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error inesperado", "Ha ocurrido un error inesperado al intentar obtener el objeto.\nDetalle: LcBdUser::get(param): "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
