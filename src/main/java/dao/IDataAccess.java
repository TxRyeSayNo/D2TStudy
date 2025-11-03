/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author admin
 */
public interface IDataAccess<T> {
    
    // Create
    boolean insert(T obj);
    
    // Read
    T findById(int id);
    List<T> findAll();
    
    // Update
    boolean update(T obj);
    
    // Delete
    boolean delete(int id);
}
