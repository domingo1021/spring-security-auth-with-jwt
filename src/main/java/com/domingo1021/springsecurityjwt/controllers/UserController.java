package com.domingo1021.springsecurityjwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user APIs that require prior authentication / authorization.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public String userHealth() {
        return "User is healthy.";
    }

    @GetMapping("/admins")
    public String admins() {
        return "Admin is healthy ";
    }
}
