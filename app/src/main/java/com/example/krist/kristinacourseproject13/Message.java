package com.example.krist.kristinacourseproject13;

import java.util.Date;

public class Message {

    private String textMessage;
    private String author;
    private long timeMessage;
    private String roomId;

    public Message(){

    }

    public Message(String textMessage, String author, String roomId){
        this.textMessage = textMessage;
        this.author = author;
        this.roomId = roomId;

        timeMessage = new Date().getTime();
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(long timeMessage) {
        this.timeMessage = timeMessage;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
