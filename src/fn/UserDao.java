/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import entities.User;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import sync.Cmp;
import sync.user.GbVlUser;
import sync.user.LcBdUser;
import sync.user.RmBdUser;

/**
 *
 * @author jorge
 */
public class UserDao {
    private static GbVlUser global = new GbVlUser();
    private static LcBdUser local = new LcBdUser();
    private static RmBdUser remote = new RmBdUser();
    
    public static boolean addUser(String nombre, String username, String pass, int tipo) throws SQLException, ClassNotFoundException{
        User user =  new User(0, nombre, username, pass, tipo, 1, new Date());
        if(Cmp.isOnline()){
            user.setId(remote.obtenerId());
            if(!remote.exist(user.getUsername()))
                return sync.Sync.add(local, remote, global, user, ""+user.getId());
            else{
                OptionPane.showMsg("No se puede crear nuevo usuario", "El Username ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el username.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede crear nuevo usuario", "Para poder ingresar un nuevo registro debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static boolean updateUser(int id, String nombre, String username, String pass, int tipo, int estado) throws SQLException, ClassNotFoundException{
        User user =  new User(id, nombre, username, pass, tipo, estado, new Date());//actualizamos la ultima fecha de modificacion
        if(Cmp.isOnline()){
            if(!remote.exist(user.getUsername()))//valida si ya existe el username
                return sync.Sync.add(local, remote, global, user, ""+user.getId());
            else{
                OptionPane.showMsg("No se puede actualizar usuario", "El Username ya se encuentra utilizado,\n"
                        + "Para poder ingresar un nuevo registro debes cambiar el username.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede actualizar el usuario", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static boolean deleteUser(String username) throws SQLException, ClassNotFoundException{
        User user =  null;
        if(Cmp.isOnline()){
            user =  (User)remote.get(username);
            if(user != null){//valida si ya existe el username
                user.setEstado(0);
                user.setLastUpdate(new Date());//actualizamos la ultima fecha de modificacion
                return sync.Sync.add(local, remote, global, user, ""+user.getId());
            }else{
                OptionPane.showMsg("No se puede eliminar usuario", "El usuario no existe.", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            OptionPane.showMsg("No se puede eliminar el usuario", "Para poder ejecutar esta operación debes tener acceso a internet.", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
}
