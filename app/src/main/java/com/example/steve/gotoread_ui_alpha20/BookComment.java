package com.example.steve.gotoread_ui_alpha20;

/**
 * Created by Steve on 2017/9/23.
 */

public class BookComment {
    private String userid;
    private String comment;
    private int sign;

    public BookComment(String userid, String comment, int sign){
        this.userid = userid;
        this.comment = comment;
        this.sign = sign;
    }

    public String getUserid() {
        return userid;
    }

    public String getComment() {
        return comment;
    }

    public int getSign() {
        return sign;
    }
}


