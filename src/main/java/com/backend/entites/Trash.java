package com.backend.entites;

public class Trash {
    int level ;
    String state;
    String address;

    public Trash(){
        this.level=0;
        this.state= null;
        this.address= null;
    }

    public Trash(String address, int level, String state){
        this.level=level ;
        this.state= state ;
        this.address= address ;
    }


    public int getLevel() {
        return level;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setState(String state) {
        this.state = state;
    }

}
