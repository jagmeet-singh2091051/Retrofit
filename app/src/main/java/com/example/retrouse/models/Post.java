package com.example.retrouse.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String email;
    private String password;

    public Post() {
    }

    public Post(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
