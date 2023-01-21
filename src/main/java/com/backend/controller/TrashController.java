package com.backend.controller;

import com.backend.Functions_bdd.db;
import com.backend.entites.Trash;
import com.sun.jdi.event.ExceptionEvent;
import jakarta.websocket.server.PathParam;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class TrashController {
    private static Logger logger = Logger.getLogger(TrashController.class);
    @PostMapping("/api/trash/add")
    public void addTrash(
            @PathParam("address") String address,
            @PathParam("state") String state,
            @PathParam("level") int level) {
        try {
            db.connect();
            db.addTash(address, level, state);
            db.deconnect();
            logger.info("nouvelle poubelle à "+address+"ajouté");
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @DeleteMapping("/api/trash/delete")
    public void deleteTrash(@PathParam("address") String address) {
        try {
            db.connect();
            db.deleteAddress(address);
            db.deconnect();
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping("/api/trash/modify/address")
    public void modifyAddress(@PathParam("oldAddress") String oldAddress,
                           @PathParam("newAddress") String newAddress) {
        try {
            db.connect();
            db.modifyAddressTrash(oldAddress,newAddress);
            db.deconnect();
        }
        catch (Exception e){
            logger.error((e));
        }
    }

    @PutMapping("/api/trash/modify/level")
    public void modifyLevel(@PathParam("address") String address,
                           @PathParam("level") int level) {
        try {
            db.connect();
            db.modifyLevelTrash(address,level);
            db.deconnect();
        }
        catch (Exception e){
            logger.error(e);
        }
    }

    @PutMapping("/api/trash/modify/state")
    public void modifyLevel(@PathParam("address") String address,
                            @PathParam("state") String state) {
        try {
            db.connect();
            db.modifyStateTrash(address,state);
            db.deconnect();
        }
        catch (Exception e){
            logger.error(e);
        }

    }

    @GetMapping("/api/trash/get/all")
    public ArrayList<Trash> getAll() {
        ArrayList<Trash> res = new ArrayList<>() ;
        try {
            db.connect();
            res =db.getAllTrash();
            db.deconnect();
        }
        catch (Exception e){
            logger.error(e);
        }
        return res ;
    }

    @GetMapping("/api/trash/get/data")
    public Trash getInfo(@PathParam("address") String address) {
        Trash res = new Trash() ;
        try {
            db.connect();
            res = db.getTrashInfo(address);
            db.deconnect();
        }
        catch (Exception e){
            logger.error(e);
        }
        return res;
    }
}
