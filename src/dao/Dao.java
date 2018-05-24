/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author jorge
 */
public interface Dao {
    public boolean add(Object object);
    public boolean update(Object object);
    public boolean delete(String id);
    public Object get(String idObject);
    public void sincronize();
}
