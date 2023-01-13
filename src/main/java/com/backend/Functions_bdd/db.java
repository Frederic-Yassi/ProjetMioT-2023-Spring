package com.backend.Functions_bdd;

import com.backend.autres.Trash;
import com.backend.autres.User;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class db {
    static Connection con = null ;
    public static void testConnect()throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/mydb?characterEncoding=utf8", "root","root");
            System.out.println("connection base de données ok");
        } catch (Exception e) {
            System.out.println("Erreur de connection");
            throw e ;
        }
    }

    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/mydb?characterEncoding=utf8", "root","root");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Erreur de connection");
        }
        System.out.println("connection base de données ok");
    }
    public static void deconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String email ,String name,String password,String role ,String number,String address) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("INSERT INTO users (email,name,password,role,number,address) values('%1$s','%2$s','%3$s','%4$s','%5$s','%6$s')",email,name,password,role,number,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("DELETE FROM users WHERE id=%1$d ",id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void modifyEmailUser(int id,String email) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set email='%1$s' WHERE id=%2$d",email,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void modifyNameUser(int id,String name) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set name='%1$s' WHERE id=%2$d",name,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void modifyPwdUser(int id,String pwd) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set password='%1$s' WHERE id=%2$d",pwd,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void modifyNumUser(int id,String num) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set number='%1$s' WHERE id=%2$d",num,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modifyRoleUser(int id,String role) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set role='%1$s' WHERE id=%2$d",role,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modifyAddressUser(int id,String address) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE users set address='%1$s' WHERE id=%2$d",address,id);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getAllUser() {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
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
                // ...
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static int getIdUser(String name,String pwd) {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public static User getUserInfo(int id) {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("SELECT id,email,name,number,role,address from users WHERE id=%1$d ",id);
        User result = new User();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            // on parcourt l'ensemble des résultats retourné par la requête
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static void template() {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql = "select * from info ";
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                String titre = resultSet.getString("");
                java.sql.Date dateSortie = resultSet.getDate("date");
                long duree = resultSet.getLong("duree");

                // ...
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void addTash(String address, int level, String state) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("INSERT INTO trash (address,level,state) values('%1$s',%2$d,'%3$s')",address,level,state);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteAddress(String address) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("DELETE FROM trash WHERE address='%1$s' ",address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modifyLevelTrash(String address, int level) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE trash set level=%1$d WHERE address='%2$s'",level,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modifyAddressTrash(String oldAddress,String newAddress) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE trash set address='%1$s' WHERE address='%2$s'",newAddress,oldAddress);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void modifyStateTrash(String address, String state) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("UPDATE trash set state='%1$s' WHERE address='%2$s' ",state,address);
        try {
            s = con.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<Trash> getAllTrash() {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql = "select address,level,state from trash ";
        ArrayList<Trash> result = new ArrayList<Trash>();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                result.add(new Trash(
                        resultSet.getString("address"),
                        resultSet.getInt("level"),
                        resultSet.getString("state")
                ));
                // ...
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public static Trash getTrashInfo(String address) {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("SELECT address,level,state from trash WHERE address='%1$s' ",address);
        Trash result = new Trash();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                result.setLevel(resultSet.getInt("level"));
                result.setState(resultSet.getString("state"));
                result.setAddress(resultSet.getString("address"));
                // ...
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
