package com.example.steve.gotoread_ui_alpha20;

/**
 * Created by Steve on 2017/9/23.
 */

public class Book {
    private String name;
    private String author;
    private String score;

    public Book(String name, String author, String score){
        this.name = name;
        this.author = author;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getScore() {
        return score;
    }
}


