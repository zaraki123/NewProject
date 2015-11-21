package com.example.nelson.caliplay.data;

import java.util.List;

/**
 * Created by Zaraki on 21/11/2015.
 */
public interface Dao<T> {

    long save(T type);
    void update(T type);
    void delete(T type);
    T get(long id);
    List<T> getAll();

}
