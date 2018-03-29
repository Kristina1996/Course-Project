package com.example.krist.kristinacourseproject13;


import java.util.Date;

public class Vote {

    private String text;
    private String author;
    private String answer1;
    private String answer2;
    private long time;
    private String street;
    private String house;
    private int Count1 = 0;
    private int Count2 = 0;

    public Vote(){

    }

    public Vote(String text, String author, String answer1, String answer2, String street, String house){
        this.text = text;
        this.author = author;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.street = street;
        this.house = house;

        time = new Date().getTime();
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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        house = house;
    }

    public int getCount1() {
        return Count1;
    }

    public void setCount1(int count1) {
        Count1 = count1;
    }

    public int getCount2() {
        return Count2;
    }

    public void setCount2(int count2) {
        Count2 = count2;
    }
}

