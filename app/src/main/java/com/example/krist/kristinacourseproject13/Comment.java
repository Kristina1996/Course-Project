package com.example.krist.kristinacourseproject13;

import java.util.Date;

public class Comment {

    private String text;
    private String author;
    private String idVote;

    public Comment(){

    }

    public Comment(String text, String author, String idVote){
        this.text = text;
        this.author = author;
        this.idVote = idVote;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdVote() {
        return idVote;
    }

    public void setIdVote(String idVote) {
        this.idVote = idVote;
    }

}
