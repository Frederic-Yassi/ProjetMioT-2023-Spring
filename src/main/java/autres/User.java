package autres;

public class User {
    int id;
    String name;
    String role;
    String num;
    public User(int id, String name, String role, String num){
        this.id=id;
        this.name=name;
        this.role=role;
        this.num=num;
    }
    public User(){
        this.id=-1;
        this.name=null;
        this.role=null;
        this.num=null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
