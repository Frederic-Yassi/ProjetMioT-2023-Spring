package com.backend.Functions_bdd;

import com.backend.entites.Trash;
import com.backend.entites.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.backend.ProjetMioTSpringApplication.env;

public class db {
    static Connection con = null ;
    private static Logger logger = Logger.getLogger(db.class);

    public static void testConnect()throws Exception {
        try {
            if(env=="docker"){
                con = (Connection) DriverManager.getConnection("jdbc:mysql://maria_db:3306/mydb?characterEncoding=utf8","dev","passwordev");
            }
            else{
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/mydb?characterEncoding=utf8","root","root");
            }
        } catch (Exception e) {
            logger.debug("base de données ko");
            throw e;
        }
    }

    public static void connect() throws Exception{
        try {
            if(env=="docker"){
                con = (Connection) DriverManager.getConnection("jdbc:mysql://maria_db:3306/mydb?characterEncoding=utf8","dev","passwordev");
            }
            else{
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/mydb?characterEncoding=utf8","root","root");
            }
        } catch (Exception e) {
            logger.debug("Impossible de se connecter à la BDD");
            throw e;
        }
        logger.debug("connection à base de données ok");
    }
    public static void deconnect() throws Exception{
        try {
            con.close();
        } catch (SQLException e) {
            logger.debug("Impossible de se deconnecter à la BDD");
            throw e;
        }
        logger.debug("deconnection à base de données ok");
    }


    public static void addUser(String email ,String name,String password,String role ,String number,String address)throws Exception {
        Statement s;
        String sql =  String.format("INSERT INTO users (email,name,password,role,number,address) values('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s')",email,name,password,role,number,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void deleteUser(int id)throws Exception {
        Statement s;
        String sql =  String.format("DELETE FROM users WHERE id=%1$d ",id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }
    public static void modifyEmailUser(int id,String email)throws Exception {
        Statement s;
        String sql =  String.format("UPDATE users set email='%1$s' WHERE id=%2$d",email,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {// TODO Auto-generated catch block
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }
    
    public static void modifyNameUser(int id,String name) throws Exception{
        Statement s;
        String sql =  String.format("UPDATE users set name='%1$s' WHERE id=%2$d",name,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }


    public static void modifyPwdUser(int id,String pwd)throws Exception {
        Statement s;
        String sql =  String.format("UPDATE users set password='%1$s' WHERE id=%2$d",pwd,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }


    public static void modifyNumUser(int id,String num) throws Exception {
        Statement s;
        String sql =  String.format("UPDATE users set number='%1$s' WHERE id=%2$d",num,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void modifyRoleUser(int id,String role)throws Exception {
        Statement s;
        String sql =  String.format("UPDATE users set role='%1$s' WHERE id=%2$d",role,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void modifyAddressUser(int id,String address)throws Exception {
        Statement s;
        String sql =  String.format("UPDATE users set address='%1$s' WHERE id=%2$d",address,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static ArrayList<User> getAllUser() throws Exception{
        Statement s;
        String sql = "select id,email,name,number,role,address from users ";
        ArrayList<User> result = new ArrayList<User>();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
                // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getString("number"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
        return result;
    }

    public static int getIdUser(String name,String pwd) throws SQLException {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("SELECT id from users WHERE name='%1$s' AND password='%2$s' ",name,pwd);
        int res=-1;
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                res = resultSet.getInt("id");
                // ...
            }

        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
        return res;
    }

    public static User getUserInfo(int id) throws SQLException {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("SELECT id,email,name,number,role,address from users WHERE id=%1$d ",id);
        User result = new User();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setEmail(resultSet.getString("email"));
                result.setName(resultSet.getString("name"));
                result.setNum(resultSet.getString("number"));
                result.setRole(resultSet.getString("role"));
                result.setAddress(resultSet.getString("address"));
                // ...
            }
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
        return result;
    }

    public static void template() throws SQLException {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql = "select * from info ";
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                String titre = resultSet.getString("");
                java.sql.Date dateSortie = resultSet.getDate("date");
                long duree = resultSet.getLong("duree");

            }

        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void addTash(String address, int level, String state) throws SQLException {
        Statement s;
        String sql =  String.format("INSERT INTO trash (address,level,state) values('%1$s',%2$d,'%3$s')",address,level,state);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void deleteAddress(String address) throws SQLException {
        Statement s;
        String sql =  String.format("DELETE FROM trash WHERE address='%1$s' ",address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void modifyLevelTrash(String address, int level) throws SQLException {
        Statement s;
        String sql =  String.format("UPDATE trash set level=%1$d WHERE address='%2$s'",level,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void modifyAddressTrash(String oldAddress,String newAddress) throws SQLException {
        Statement s;
        String sql =  String.format("UPDATE trash set address='%1$s' WHERE address='%2$s'",newAddress,oldAddress);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static void modifyStateTrash(String address, String state) throws SQLException {
        Statement s;
        String sql =  String.format("UPDATE trash set state='%1$s' WHERE address='%2$s' ",state,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
    }

    public static ArrayList<Trash> getAllTrash() throws SQLException {
        Statement s;
        String sql = "select address,level,state from trash ";
        ArrayList<Trash> result = new ArrayList<Trash>();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new Trash(
                        resultSet.getString("address"),
                        resultSet.getInt("level"),
                        resultSet.getString("state")
                ));

            }
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
        return result;
    }

    public static Trash getTrashInfo(String address) throws SQLException {
        Statement s;
        String sql =  String.format("SELECT address,level,state from trash WHERE address='%1$s' ",address);
        Trash result = new Trash();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            while (resultSet.next()) {
                result.setLevel(resultSet.getInt("level"));
                result.setState(resultSet.getString("state"));
                result.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            logger.debug("erreur dans la requête sql");
            throw e;
        }
        return result;
    }

}
