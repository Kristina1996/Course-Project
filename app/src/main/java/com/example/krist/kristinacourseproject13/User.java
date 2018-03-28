package com.example.krist.kristinacourseproject13;

public class User {
    private String Name;
    private String Password;
    private String Street;
    private String House;

    public User(){

    }

    public User(String name, String password, String street, String house){
        Name = name;
        Password = password;
        Street = street;
        House = house;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
