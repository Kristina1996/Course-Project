package com.example.krist.kristinacourseproject13;

public class User {
    private String Name;
    private String Password;
    private String Street;
    private String House;
    private String Role;

    public User(){

    }

    public User(String name, String password, String street, String house, String role){
        Name = name;
        Password = password;
        Street = street;
        House = house;
        Role = role;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

}
