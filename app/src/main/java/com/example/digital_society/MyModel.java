package com.example.digital_society;

public class MyModel {
    int id;
    String societyname;
    String username;
    String password;

    public MyModel(int id, String societyname, String username, String password) {
        this.id = id;
        this.societyname = societyname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSocietyname() {
        return societyname;
    }

    public void setSocietyname(String societyname) {
        this.societyname = societyname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChairman_password() {
        return password;
    }

    public void setChairman_password(String password) {
        this.password = password;
    }
}
