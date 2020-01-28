package com.DigitaleFactuur.db;

import com.DigitaleFactuur.models.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO<User> {

    private ResultSet result = null;

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    private static String getUserByEmailQuery(String email) {
        return "SELECT * FROM user WHERE email = '" + email + "';";
    }

    private static String getUserByIDQuery(int id) {
        return "SELECT * FROM user WHERE id = '" + id + "';";
    }

    private static String getUserIDByEmailQuery(String email) {
        return "SELECT id FROM user WHERE email = '" + email + "';";
    }

    private static String deleteUserByIDQuery(int id) {
        return "DELETE FROM user WHERE id = '" + id + "';";
    }

    public User getUserByEmail(String email) {
        try {
            result = DatabaseConnector.executeDatabaseQuery(UserDAO.getUserByEmailQuery(email));
            if (result != null) {
                result.next();
                return createUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return null;
    }

    public User getUserByID(int id) {
        try {
            result = DatabaseConnector.executeDatabaseQuery(UserDAO.getUserByIDQuery(id));
            if (result != null) {
                result.next();
                return createUser();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return null;
    }

    public int getUserIDByEmail(String email) {
        int userID = 0;
        try {
            result = DatabaseConnector.executeDatabaseQuery(UserDAO.getUserIDByEmailQuery(email));
            if (result != null) {
                result.next();
                userID = result.getInt("id");
            }
        } catch (SQLException ignored) {
        } finally {
            DatabaseConnector.closeDatabaseConnection(result);
        }
        return userID;
    }

    public void deleteUserByID(int id) {
        DatabaseConnector.executeDatabaseUpdate(UserDAO.deleteUserByIDQuery(id));
    }

    public User saveUser(User user) {
        return persist(user);
    }

    private User createUser() throws SQLException {
        return new User(
                result.getString("email"),
                result.getString("username"),
                result.getString("password"));
    }
}