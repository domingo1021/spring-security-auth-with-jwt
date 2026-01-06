package com.domingo1021.springsecurityjwt.models;

public class Admin extends User {

    public static final String ADMIN = "ADMIN";

    public Admin(String username, String hashedPassword) {
        super(username, hashedPassword, ADMIN);
    }
}
