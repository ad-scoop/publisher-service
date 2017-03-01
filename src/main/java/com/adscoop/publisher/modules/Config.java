package com.adscoop.publisher.modules;

/**
 * Created by thokle on 13/01/2017.
 */
public class Config {


    private String driver_class_name;
    private String username;
    private String password;
    private String neo4Url;

    public String getDriver_class_name() {
        return driver_class_name;
    }

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
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

    public String getNeo4Url() {
        return neo4Url;
    }

    public void setNeo4Url(String neo4Url) {
        this.neo4Url = neo4Url;
    }
}
