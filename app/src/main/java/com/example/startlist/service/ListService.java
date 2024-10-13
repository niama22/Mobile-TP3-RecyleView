package com.example.startlist.service;

import com.example.startlist.Bean.star;
import com.example.startlist.Dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class ListService implements IDao<star> {
    private List<star> stars;
    private static ListService instance;

    private ListService() {
        stars = new ArrayList<>();
    }

    public static ListService getInstance() {
        if (instance == null) {
            instance = new ListService();
        }
        return instance;
    }

    @Override
    public Boolean create(star o) {
        return stars.add(o);
    }

    @Override
    public Boolean delete(star o) {
        return stars.remove(o);
    }

    @Override
    public Boolean update(star o) {
        for (star s : stars) {
            if (o.getId() == s.getId()) {
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStars(o.getStars());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<star> findAll() {
        return new ArrayList<>(this.stars);
    }

    @Override
    public star findById(int id) {
        for (star s : stars) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
