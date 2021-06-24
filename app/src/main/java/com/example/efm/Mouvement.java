package com.example.efm;

public class Mouvement {

    Integer id;
    String article;
    Float quatite;
    Boolean typeMouvement;


    public Mouvement() {
    }

    public Mouvement(String article, Float quatite, Boolean typeMouvement) {
        this.article = article;
        this.quatite = quatite;
        this.typeMouvement = typeMouvement;
    }


    public Mouvement(Integer id, String article, Float quatite, Boolean typeMouvement) {
        this.id = id;
        this.article = article;
        this.quatite = quatite;
        this.typeMouvement = typeMouvement;
    }
}
