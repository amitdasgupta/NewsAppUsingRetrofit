package com.example.amitdasgupta.newsappusingretrofit.model;

/**
 * Created by AMIT DAS GUPTA on 29-09-2017.
 */

public class Users {
    public Users() {
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {

        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_id() {
        return user_id;
    }

    public Users(String username, String email, String user_id) {

        this.username = username;
        this.email = email;
        this.user_id = user_id;
    }

    String username;
    String email;
    String user_id;
}
