package com.backend.controller;

import com.backend.Functions_bdd.db;
import com.backend.entites.User;
import jakarta.websocket.server.PathParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @PostMapping("/api/user/add")
    public void addUser(
            @PathParam("email") String email,
            @PathParam("name") String name,
            @PathParam("pwd") String pwd,
            @PathParam("role") String role,
            @PathParam("num") String num,
            @PathParam("address") String address) {
        try{
            db.connect();
            db.addUser(email,name,pwd,role,num,address);
            int id = getId(name,pwd);
            db.deconnect();
            logger.info("nouvel utilisateur ajouté :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }

    }

    @DeleteMapping("/api/user/delete")
    public void deleteUser(@PathParam("id") int id) {
        try {
            db.connect();
            String name = db.getUserInfo(id).getName();
            db.deleteUser(id);
            db.deconnect();
            logger.info("utilisateur "+name+"supprimé");
        }
        catch (Exception e){
            logger.error(e);
        }

    }

    @PutMapping("/api/user/modify/email")
    public void modifyEmail(@PathParam("id") int id,
                           @PathParam("email") String email) {

        try {
            db.connect();
            db.modifyEmailUser(id,email);
            db.deconnect();
            logger.info("modification d'email de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping("/api/user/modify/name")
    public void modifyName(@PathParam("id") int id,
                           @PathParam("name") String name) {
        try{
            db.connect();
            db.modifyNameUser(id,name);
            db.deconnect();
            logger.info("modification de nom de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping("/api/user/modify/role")
    public void modifyRole(@PathParam("id") int id,
                           @PathParam("role") String role) {

        try{
            db.connect();
            db.modifyRoleUser(id,role);
            db.deconnect();
            logger.info("modification du role de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }

    }

    @PutMapping("/api/user/modify/num")
    public void modifyNum(@PathParam("id") int id,
                          @PathParam("num") String num) {
        try{
            db.connect();
            db.modifyNumUser(id,num);
            db.deconnect();
            logger.info("modification du numero de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping("/api/user/modify/pwd")
    public void modifyPwd(@PathParam("id") int id,
                          @PathParam("pwd") String pwd) {
        try{
            db.connect();
            db.modifyPwdUser(id,pwd);
            db.deconnect();
            logger.info("modification du password de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }

    }

    @PutMapping("/api/user/modify/address")
    public void modifyAddress(@PathParam("id") int id,
                          @PathParam("address") String address) {
        try{
            db.connect();
            db.modifyAddressUser(id,address);
            db.deconnect();
            logger.info("modification de l'adresse de l'utilisateur :"+id);
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @GetMapping("/api/user/get")
    public int getId(@PathParam("name") String name,
                     @PathParam("pwd") String pwd) {
        int res=-1;
        try{
            db.connect();
            res=db.getIdUser(name,pwd);
            db.deconnect();
            logger.info("recherche Id");
        }
        catch (Exception e){
            logger.error(e);
        }
        return res;
    }

    @GetMapping("/api/user/get/all")
    public ArrayList<User> getAll() {
        ArrayList<User> res = new ArrayList<>();
        try{
            db.connect();
            res =db.getAllUser();
            db.deconnect();
            logger.info("recuperation des utilisateurs");
        }
        catch (Exception e){
            logger.error(e);
        }
        return res ;
    }

    @GetMapping("/api/user/get/data")
    public User getInfo(@PathParam("id") int id) {
        User res = new User();
        try{
            db.connect();
            res = db.getUserInfo(id);
            db.deconnect();
            logger.info("recuperation des informations de l'utilisateur : "+id);
        }
        catch (Exception e){
            logger.error(e);
        }
        return res;
    }
}
