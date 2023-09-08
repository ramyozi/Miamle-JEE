/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.dao;

import java.util.*;


/**
 *
 * @author stag
 */
public interface Crudable<T> {

    Collection<T> getAll();

    int count();

    T getById(int id);

    void insert(T obj);

    void update(T obj);

    void delete(T obj);

    void delete(int id);
}




