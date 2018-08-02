/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sync.entities;

import bd.LcBd;
import entities.Lente;
import fn.Log;
import fn.OptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlleivas
 */
public class LocalInventario {
    private static String className="LocalInventario";
    
    public static boolean insert(String idLente, int cantidad) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        int id = getMaxId();
        PreparedStatement insert = LcBd.obtener().prepareStatement(
                "INSERT INTO intern_stock VALUES("
                    + id + ",'"
                    + idLente + "',"
                    + cantidad + ","
                    + 1 + ")"
        );
        if (insert.executeUpdate() != 0) {
            LcBd.cerrar();
            return true;
        }
        return false;
    }
    
    public static ArrayList<Object> listarLentes(String idParam) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        ArrayList<Object> lista = new ArrayList<Object>();
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

        PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        String idLente = "";
        int stock = 0;
        while (datos.next()) {
            idLente = datos.getString("len_id");
            stock = datos.getInt("len_stock")-getStock(idLente);
            lista.add(new Lente(
                idLente,
                datos.getString("len_color"),
                datos.getString("len_tipo"),
                datos.getString("len_marca"),
                datos.getString("len_material"),
                datos.getInt("len_flex"),
                datos.getInt("len_clasificacion"),
                datos.getString("len_descripcion"),
                datos.getInt("len_precio_ref"),
                datos.getInt("len_precio_act"),
                stock,
                datos.getInt("len_stock_min"),
                datos.getString("inventario_inv_id"),
                datos.getInt("len_estado"),
                datos.getDate("len_last_update"),
                datos.getInt("len_last_hour")
                )
            );
        }
        LcBd.cerrar();
        return lista;
    }
    
    public static boolean deleteAllRegistry(String idLente) {
        try{
            PreparedStatement insert = LcBd.obtener().prepareStatement("UPDATE intern_stock set estado = 0 WHERE id_lente = '" + idLente+"' AND estado = 1");
            insert.executeUpdate();
            LcBd.cerrar();
            return true;
        }catch( ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
            OptionPane.showMsg("Error inesperado", "Ocurrió un error al intentar borrar los registros temporales del stock\n"
                    + "en :"+className+"\n\n"+Log.getLog(), 3);
            return false;
        }
    }

    public static int getMaxId() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Log.setLog(className, Log.getReg());
        int id = 0;
        String sql = "SELECT MAX(id) as suma FROM intern_stock";
        PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                id = datos.getInt("suma");
            }
            LcBd.cerrar();
        return id + 1;
    }
    
    /**
     * retorna la cantidad total de registros de stock que no han sido eliminados
     * @param idLente
     * @return 
     */
    public static int getStock(String idLente){
        Log.setLog(className, Log.getReg());
        int cantidad = 0;
        try{
            String sql = "SELECT SUM(stock) as cantidad FROM intern_stock WHERE id_lente = '"+idLente+"' AND estado = 1";
            PreparedStatement consulta = LcBd.obtener().prepareStatement(sql);
                ResultSet datos = consulta.executeQuery();
                while (datos.next()) {
                    cantidad = datos.getInt("cantidad");
                }
                LcBd.cerrar();
        }catch(Exception e){
            return 0;
        }
        return cantidad;
    }
}
