package com.backend.controller;

import com.backend.Functions_bdd.db;
import com.backend.autres.User;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    @PostMapping("/api/user/add")
    public void addUser(
            @PathParam("email") String email,
            @PathParam("name") String name,
            @PathParam("pwd") String pwd,
            @PathParam("role") String role,
            @PathParam("num") String num,
            @PathParam("address") String address) {
        System.out.println("name :"+name);
        System.out.println("pwd :"+pwd);
        db.connect();
        db.addUser(email,name,pwd,role,num,address);
        db.deconnect();
    }

    @DeleteMapping("/api/user/delete")
    public void deleteUser(@PathParam("id") int id) {
        System.out.println("id :"+id);
        db.connect();
        db.deleteUser(id);
        db.deconnect();
    }

    @PutMapping("/api/user/modify/email")
    public void modifyEmail(@PathParam("id") int id,
                           @PathParam("email") String email) {
        db.connect();
        db.modifyEmailUser(id,email);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/user/modify/name")
    public void modifyName(@PathParam("id") int id,
                           @PathParam("name") String name) {
        db.connect();
        db.modifyNameUser(id,name);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/user/modify/role")
    public void modifyRole(@PathParam("id") int id,
                           @PathParam("role") String role) {
        db.connect();
        db.modifyRoleUser(id,role);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/user/modify/num")
    public void modifyNum(@PathParam("id") int id,
                          @PathParam("num") String num) {
        db.connect();
        db.modifyNumUser(id,num);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/user/modify/pwd")
    public void modifyPwd(@PathParam("id") int id,
                          @PathParam("pwd") String pwd) {
        db.connect();
        db.modifyPwdUser(id,pwd);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/user/modify/address")
    public void modifyAddress(@PathParam("id") int id,
                          @PathParam("address") String address) {
        db.connect();
        db.modifyAddressUser(id,address);
        db.deconnect();
        System.out.println("start method work");
    }

    @GetMapping("/api/user/get")
    public int getId(@PathParam("name") String name,
                     @PathParam("pwd") String pwd) {
        db.connect();
        int res=db.getIdUser(name,pwd);
        db.deconnect();
        System.out.println("delay method  work");
        return res;
    }

    @GetMapping("/api/user/get/all")
    public ArrayList<User> getAll() {
        db.connect();
        ArrayList<User> res =db.getAllUser();
        db.deconnect();
        System.out.println("delay method  work");
        return res ;
    }

    @GetMapping("/api/user/get/data")
    public User getInfo(@PathParam("id") int id) {
        db.connect();
        User res = db.getUserInfo(id);
        db.deconnect();
        System.out.println("delay method  work");
        return res;
    }
}
