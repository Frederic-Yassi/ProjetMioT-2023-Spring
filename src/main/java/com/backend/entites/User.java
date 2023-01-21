package com.backend.entites;

public class User {
    int id;
    String email;
    String name;
    String role;
    String num;

    String address;
    public User(int id,String email, String name, String role, String num,String address){
        this.id=id;
        this.email=email;
        this.name=name;
        this.role=role;
        this.num=num;
        this.address=address;
    }
    public User(){
        this.id=-1;
        this.email=null;
        this.name=null;
        this.role=null;
        this.num=null;
        this.address=null;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(String address) {this.address = address;}

    public void setEmail(String email) { this.email = email;}
}
