package com.example.steve.gotoread_ui_alpha20;

/**
 * Created by Steve on 2017/9/23.
 */

public class Vocabulary {
    private String word;
    private String translation;
    private String time;

    public Vocabulary(String word, String tranlation, String time){
        this.word = word;
        this.translation = translation;
        this.time = time;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getTime() {
        return time;
    }
}


