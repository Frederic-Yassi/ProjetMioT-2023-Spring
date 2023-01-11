package com.backend;

import Functions_bdd.db;
import autres.User;
import jakarta.websocket.server.PathParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@SpringBootApplication
public class ProjetMioTSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProjetMioTSpringApplication.class, args);

	}

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

	@PostMapping("/api/user/add")
	public void add(
			@PathParam("name") String name,
			@PathParam("pwd") String pwd,
			@PathParam("role") String role,
			@PathParam("num") String num) {
		System.out.println("name :"+name);
		System.out.println("pwd :"+pwd);
		db.connect();
		db.addUser(name,pwd,role,num);
		db.deconnect();
	}

	@DeleteMapping("/api/user/delete")
	public void delete(@PathParam("id") int id) {
		System.out.println("id :"+id);
		db.connect();
		db.deleteUser(id);
		db.deconnect();
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
