package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.UserDAO;
import com.DigitaleFactuur.models.User;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public User getUserByID(int id) {
        return userDAO.getUserByID(id);
    }

    public int getUserIDByEmail(String email) {
        return userDAO.getUserIDByEmail(email);
    }

    public User saveUser(User user) {
        return userDAO.saveUser(user);
    }

    public void deleteUserByID(int id) {
        userDAO.deleteUserByID(id);
    }
}