/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;
import bd.RmBd;
import entities.Cristal;
import entities.User;
import fn.Log;
import fn.OptionPane;
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
public class Remote implements SyncBd{
    private static String className = "RmBdUser";
    /**
     * Agrega o modifica un objeto en la base de datos remota
     * @param usuario
     * @return true si se insertó o modificó
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static boolean update(User object) throws SQLException, ClassNotFoundException{
        Log.setLog(className,Log.getReg());
        if(object != null){
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT `us_id` FROM `usuario` WHERE `us_id`='"+object.getId()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return modificar(object);
                }    
                //////// dar formato String a fecha
                java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
                
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        "INSERT INTO usuario VALUES('"
                                +object.getId()+"', '"
                                +object.getNombre()+"', '"
                                +object.getUsername()+"', '"
                                +object.getPass()+"', '"
                                +object.getTipo()+"', '"
                                +object.getEstado()+"', '"
                                +sqlfecha+"')"
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();
                    //OptionPane.showMsg("Operación realizada correctamente", "Usuario: "+object.getUsername()+"\nId: "+object.getId()+"\nAgregado correctamente.", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
        }
        OptionPane.showMsg("Error inseperado en la operación", "Registro: "+object.getUsername()+"\nId: "+object.getId()+"\nNo se pudo insertar.\nDetalle:\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public static boolean update(Cristal object) throws SQLException, ClassNotFoundException{
        Log.setLog(className,Log.getReg());
        if(object != null){
                PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT `cri_id` FROM `cristal` WHERE `cri_id`='"+object.getId()+"'");
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    RmBd.cerrar();
                    return modificar(object);
                }    
                //////// dar formato String a fecha
                java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
                
                PreparedStatement insert = RmBd.obtener().prepareStatement(
                        "INSERT INTO cristal VALUES('"
                                +object.getId()+"', '"
                                +object.getNombre()+"', '"
                                +object.getPrecio()+"', '"
                                +object.getEstado()+"', '"
                                +sqlfecha+"')"
                               );
                if(insert.executeUpdate()!=0){
                    RmBd.cerrar();
                    
                    return true;
                }
        }
        OptionPane.showMsg("Error inseperado en la operación", "Registro: "+object.getNombre()+"\nId: "+object.getId()+"\nNo se pudo insertar.\nDetalle:\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public static boolean modificar(User object) throws SQLException, ClassNotFoundException{
        Log.setLog(className,Log.getReg());
        PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM `usuario` WHERE `us_id`='"+object.getId()+"'");
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            Date dsp_fecha= new Date();
            try {
                dsp_fecha = datos.getDate("us_last_update");
            } catch (Exception e) {
                OptionPane.showMsg("Error al convertir fecha", "sync.object.RmBdUser::modificar(User object):\nSe cayó al intentar convertir la fecha", JOptionPane.ERROR_MESSAGE);
            }
            if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                return false;
            }
        }
        java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
        
        PreparedStatement insert = RmBd.obtener().prepareStatement(
                "UPDATE `usuario` set `us_nombre` = '"+object.getNombre()
                        +"', `us_username` = '"+object.getUsername()
                        +"', `us_pass` = '"+object.getPass()
                        +"', `us_tipo` = '"+object.getTipo()
                        +"', `us_estado` = '"+object.getEstado()
                        +"', `us_last_update` = '"+sqlfecha
                        +"' WHERE `us_id` = '"+object.getId()+"' AND us_last_update <= '"+sqlfecha+"'");
        if(insert.executeUpdate()!=0){
            RmBd.cerrar();
            //OptionPane.showMsg("Operación realizada correctamente", "Usuario actualizado con éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else{
            RmBd.cerrar();
            return true;
        }
    }
    
    public static boolean modificar(Cristal object) throws SQLException, ClassNotFoundException{
        Log.setLog(className,Log.getReg());
        PreparedStatement consulta = RmBd.obtener().prepareStatement("SELECT * FROM `cristal` WHERE `cri_id`='"+object.getId()+"'");
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            Date dsp_fecha= new Date();
            try {
                dsp_fecha = datos.getDate("cri_last_update");
            } catch (Exception e) {
                OptionPane.showMsg("Error al convertir fecha","Se cayó al intentar convertir la fecha.\nDetalle:\n"+Log.getLog(), JOptionPane.ERROR_MESSAGE);
            }
            if(!fn.date.Cmp.localIsNewOrEqual(object.getLastUpdate(), dsp_fecha)){
                return false;
            }
        }
        java.sql.Date sqlfecha = new java.sql.Date(object.getLastUpdate().getTime());//la transforma a sql.Date
        
        PreparedStatement insert = RmBd.obtener().prepareStatement(
                "UPDATE `cristal` set `cri_nombre` = '"+object.getNombre()
                        +"', `cri_precio` = '"+object.getPrecio()
                        +"', `cri_estado` = '"+object.getEstado()
                        +"', `cri_last_update` = '"+sqlfecha
                        +"' WHERE `cri_id` = '"+object.getId()+"' AND cri_last_update <= '"+sqlfecha+"'");
        if(insert.executeUpdate()!=0){
            RmBd.cerrar();
            
            return true;
        }
        else{
            RmBd.cerrar();
            return true;
        }
    }
    
    public ArrayList<User> users(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Log.setLog(className,Log.getReg());
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
        
        PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
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
        RmBd.cerrar();
        return lista;
    }
    
    public ArrayList<Cristal> cristales(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Log.setLog(className,Log.getReg());
        String sql="SELECT * FROM cristal WHERE cri_id="+id;
        if(id==0){
        sql="SELECT * FROM cristal WHERE cri_estado=1";
        }
         if(id==-1){
        sql="SELECT * FROM cristal WHERE cri_estado=0";
        }
         if(id==-2){
        sql="SELECT * FROM cristal";
        }
        
        PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Cristal> lista = new ArrayList<>();
        while (datos.next()) {
            lista.add(new Cristal(
                    datos.getInt("cri_id")
                    , datos.getString("cri_nombre")
                    , datos.getInt("cri_precio")
                    , datos.getInt("cri_estado")
                    , datos.getDate("cri_last_update")
                    )
            );
        }
        RmBd.cerrar();
        return lista;
    }
    
    public int getMaxUserId() throws SQLException, ClassNotFoundException {
        Log.setLog(className,Log.getReg());
        String sql="SELECT MAX(`us_id`) as id_user FROM `usuario`";
        
        PreparedStatement consulta = RmBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int id=0;
        while (datos.next()) {
            id=datos.getInt("id_user");
        }
        RmBd.cerrar();
        return id+1;
    }

    @Override
    public boolean add(Object object) {
        Log.setLog(className,Log.getReg());
        try {
            if(object instanceof User)
                return update((User)object);
            if(object instanceof Cristal)
                return update((Cristal)object);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Remote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @Override
    public User getUser(String username) {
        Log.setLog(className,Log.getReg());
        try {
            for (User temp : users(-2)) {
                if(temp.getUsername().toLowerCase().equals(username.toLowerCase()))
                    return temp;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error inesperado", "Ha ocurrido un error inesperado al intentar obtener el objeto.\nDetalle: LcBdUser::get(param): "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean userExist(String username) {
        Log.setLog(className,Log.getReg());
        if(getUser(username)!=null)
            return true;
        return false;
    }
    
    public boolean cristalExist(String name) {
        Log.setLog(className,Log.getReg());
        if(getCristal(name)!=null)
            return true;
        return false;
    }

    @Override
    public Cristal getCristal(String name) {
        Log.setLog(className,Log.getReg());
        try {
            for (Cristal temp : cristales(-2)) {
                if(temp.getNombre().toLowerCase().equals(name.toLowerCase()))
                    return temp;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            OptionPane.showMsg("Error inesperado", "Ha ocurrido un error inesperado al intentar obtener el objeto.\nDetalle: LcBdUser::get(param): "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
