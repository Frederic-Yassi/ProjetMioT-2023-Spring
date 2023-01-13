package com.backend.controller;

import com.backend.Functions_bdd.db;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/api/testApi")
    public String testApi() {
        System.out.println("Accessibilité method stop work");
        return "API est effectivement accessible !";
    }

    @GetMapping("/api/testBdd")
    public String testBdd() {
        System.out.println("Accessibilité method stop work");
        try {
            db.testConnect();
        }
        catch (Exception e){
            return "Impossible de se connecter la BDD !";
        }
        return "Connection à la BDD ok !";
    }


    @GetMapping("/api/auth")
    public String Authentification(@PathParam("name") int name ,
                                   @PathParam("pwd") String pwd) {
        try {
            db.connect();
            //db.addUser("configuration", "");
            db.deconnect();
            return "Configuration effectué avec succes .";
        }
        catch(Exception e) {
            System.out.println(e);
            return "erreur de connection avec la Base de données";
        }

    }
}
