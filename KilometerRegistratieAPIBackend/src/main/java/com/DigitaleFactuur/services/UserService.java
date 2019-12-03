package com.DigitaleFactuur.services;

import com.DigitaleFactuur.db.CarDAO;
import com.DigitaleFactuur.db.DeclarationDAO;
import com.DigitaleFactuur.db.UserDAO;
import com.DigitaleFactuur.models.Declaration;
import com.DigitaleFactuur.models.User;
import com.google.common.base.Optional;

import java.sql.SQLException;

public class UserService {

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public Optional<User> findByUsername(String username){
        return dao.findByUsername(username);
    }

    public User save(User user) {
        return dao.save(user);
    }

    public User test(String email) throws SQLException {
        return dao.getUserByEmail(email);
    }

    public User getUserByID(int id) throws SQLException {
        return dao.getUserByID(id);
    }

    /**
     * Call the delete function from the dao.
     * @param id
     * @author Tom
     */
    public void delete(int id) {
        dao.deleteUserById(id);
    }

    public int getUserIDByEmail(String email){
        return dao.getUserIDByEmail(email);
    }


}