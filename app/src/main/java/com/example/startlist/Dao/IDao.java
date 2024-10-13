package com.example.startlist.Dao;

import java.util.List;

public interface IDao<T>{
       Boolean create(T o);
       Boolean update(T o);
       Boolean delete(T o);
       T findById(int id);
       List<T> findAll();
}
