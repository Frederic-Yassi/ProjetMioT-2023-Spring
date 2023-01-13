package com.backend.controller;

import com.backend.Functions_bdd.db;
import com.backend.autres.Trash;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TrashController {

    @PostMapping("/api/trash/add")
    public void addUser(
            @PathParam("address") String address,
            @PathParam("state") String state,
            @PathParam("level") int level) {

        db.connect();
        db.addTash(address,level,state);
        db.deconnect();
    }

    @DeleteMapping("/api/trash/delete")
    public void deleteUser(@PathParam("address") String address) {
        db.connect();
        db.deleteAddress(address);
        db.deconnect();
    }

    @PutMapping("/api/trash/modify/address")
    public void modifyAddress(@PathParam("oldAddress") String oldAddress,
                           @PathParam("newAddress") String newAddress) {
        db.connect();
        db.modifyAddressTrash(oldAddress,newAddress);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/trash/modify/level")
    public void modifyLevel(@PathParam("address") String address,
                           @PathParam("level") int level) {
        db.connect();
        db.modifyLevelTrash(address,level);
        db.deconnect();
        System.out.println("start method work");
    }

    @PutMapping("/api/trash/modify/state")
    public void modifyLevel(@PathParam("address") String address,
                            @PathParam("state") String state) {
        db.connect();
        db.modifyStateTrash(address,state);
        db.deconnect();
        System.out.println("start method work");
    }

    @GetMapping("/api/trash/get/all")
    public ArrayList<Trash> getAll() {
        db.connect();
        ArrayList<Trash> res =db.getAllTrash();
        db.deconnect();
        System.out.println("delay method  work");
        return res ;
    }

    @GetMapping("/api/trash/get/data")
    public Trash getInfo(@PathParam("address") String address) {
        db.connect();
        Trash res = db.getTrashInfo(address);
        db.deconnect();
        System.out.println("delay method  work");
        return res;
    }
}
