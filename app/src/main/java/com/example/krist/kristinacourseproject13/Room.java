package com.example.krist.kristinacourseproject13;


public class Room {

    private String Street;
    private String House;

    public Room(){

    }

    public Room(String street, String house){
        Street = street;
        House = house;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }
}
