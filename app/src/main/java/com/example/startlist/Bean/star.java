package com.example.startlist.Bean;

public class star {
    private int id;
    private static int comp;
    private String name;
    private String img;
    private float stars;
    public star(){}
    public star(String name,String img,float stars){
        this.id=++comp;
        this.name=name;
        this.img=img;
        this.stars=stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
