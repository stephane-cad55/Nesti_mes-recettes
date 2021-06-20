package com.example.nesti_mes_recettes.entity;

public class Ingredient {

    private int id;
    private String qtx;
    private String name;
    private String unity;
    private int check;

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQtx() {
        return qtx;
    }

    public void setQtx(String qtx) {
        this.qtx = qtx;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
