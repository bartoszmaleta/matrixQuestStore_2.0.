package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoDb;
import com.company.model.user.User;

public class LoginService {
    private UserDao userDao;

    public LoginService() {
        this.userDao = new UserDaoDb();
    }

    public User readUserWithEmailAndPassword(String email, String password) {
        return this.userDao.readUserByEmailAndPassword(email, password);
    }
}
