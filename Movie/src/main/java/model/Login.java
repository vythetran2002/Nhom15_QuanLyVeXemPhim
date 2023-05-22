package model;


import java.io.Serializable;

public class Login implements Serializable {
    private  String id;
    private  String userId;
    private  String username;
    private  String password;
    private  String userType;

    public  Login(){}
    public Login(String id, String userId, String username, String password, String userType) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

