package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by thokle on 10/12/2016.
 */
public class AuthEntity {

    @JsonProperty
private String username;
    @JsonProperty
private String password;


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
}
