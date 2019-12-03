package com.DigitaleFactuur.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static String username = "robin";
    private static String password = "Robin5000";

    public static Connection getConnection() throws SQLException {
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ipsen2?serverTimezone=UTC", username, password);

        return con;
    }
}
