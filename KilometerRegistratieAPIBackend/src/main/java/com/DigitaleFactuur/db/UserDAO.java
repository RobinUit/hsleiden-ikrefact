
package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.User;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.List;


public class UserDAO extends AbstractDAO<User> {

    Connection con;

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public String getQuery(String getValue){
        return "SELECT * FROM user WHERE email = '" + getValue + "'";
    }

    public String getIDQuery(int getValue){
        return "SELECT * FROM user WHERE id = '" + getValue + "'";
    }

    public String SQLdeleteUserById(int id){
        return "DELETE FROM user WHERE id='" + id + "';";
    }

    private String SQLgetUserIDByEmail(String email){
        return "SELECT id FROM user WHERE email = '" + email+ "'";
    }

    public User getUserByEmail(String email) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement getUserByEmail = con.prepareStatement(getQuery(email));
            ResultSet result = getUserByEmail.executeQuery();
            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getString(4));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByID(int id) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement getUserByEmail = con.prepareStatement(getIDQuery(id));
            ResultSet result = getUserByEmail.executeQuery();
            while (result.next()) {
                User user = new User(result.getString(2), result.getString(3), result.getString(4));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserIDByEmail(String email){
        int userID = 0;
        try{
            con = DatabaseConnector.getConnection();
            String getUserIDByEmail = SQLgetUserIDByEmail(email);
            PreparedStatement getIdByEmail = con.prepareStatement(getUserIDByEmail);
            ResultSet rs = getIdByEmail.executeQuery();
            if (rs.next()){
                userID = rs.getInt("id");
            }
        }catch(SQLException e){
        }
        return userID;
    }

    public void deleteUserById(int id) {
        try {
            con = DatabaseConnector.getConnection();
            PreparedStatement deleteUserById = con.prepareStatement(SQLdeleteUserById(id));
            deleteUserById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        return list(
                namedQuery(
                        "com.udemy.core.User.findAll"
                )
        );
    }
    public Optional<User> findByUsername(
            String username
            //,String password
    ) {
        return Optional.fromNullable(
                uniqueResult(
                        namedQuery("com.udemy.core.User.findByUsername")
                                .setParameter("username", username)
                        //.setParameter("password", password)
                )
        );
    }

    public User save(User user) {
        return persist(user);
    }
}