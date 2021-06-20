package com.example.nesti_mes_recettes.entity;

public class Recipe {

    String idRecipe;
    String title;
    String cat;
    String author;
    int imgId;
    int imgStar;
    int difficulty;

    public String getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(String idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgStar() { return imgStar; }

    public void setImgStar(int imgStar) { this.imgStar = imgStar; }

    public int getDifficulty() { return difficulty; }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }


    @Override
    public String toString() {
        return "Recipe{" +
                "cat='" + this.cat + '\'' +
                ", title='" + this.title + '\'' +
                ", imgId=" + this.imgId +
                ", author='" + this.author + '\'' +
                ",imgStar=" + this.imgStar +
                '}';
    }
}
