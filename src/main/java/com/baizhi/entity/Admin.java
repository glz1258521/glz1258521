package com.baizhi.entity;

import org.springframework.stereotype.Component;

/**
 * @author User
 * @time 2020/12/18-18:58
 */
@Component
public class Admin {
    private int id;
    private String username;
    private String password;
    private String status;
    private String salt;

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Admin(int id, String username, String password, String status, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
