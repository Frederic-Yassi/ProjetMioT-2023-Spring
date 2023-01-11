package Functions_bdd;

import autres.User;
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

    public static void addUser(String name,String password,String role ,String number) {
        Statement s;
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql =  String.format("INSERT INTO users (name,password,role,number) values('%1$s','%2$s','%3$s','%4$s')",name,password,role,number);
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

    public static ArrayList<User> getAllUser() {
        Statement s;
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String sql = "select id,name,number,role from users ";
        ArrayList<User> result = new ArrayList<User>();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
                // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getString("number")
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
        String sql =  String.format("SELECT id,name,number,role from users WHERE id=%1$d ",id);
        User result = new User();
        try {
            s = con.createStatement();
            ResultSet resultSet = s.executeQuery(sql);
            // on parcourt l'ensemble des résultats retourné par la requête
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setNum(resultSet.getString("number"));
                result.setRole(resultSet.getString("role"));
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
}
