package com.company.controllers;

import com.company.models.users.User;

public class AdminController {
    private User user;

    public AdminController(User user) {
        this.user = user;
        System.out.println("Admin Controller");
        System.out.println("Hello, Your email: " + this.user.getEmail());
    }

    public void init() {
        System.out.println("Admin Controller init");
    }
}
