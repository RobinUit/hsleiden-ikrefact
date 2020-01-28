package com.DigitaleFactuur.db;

import java.sql.*;

class DatabaseConnector {

    private static Connection databaseConnection = null;
    private static PreparedStatement preparedStatement = null;

    private static void openDatabaseConnection() {
        String username = "robin";
        String password = "Robin5000";

        try {
            databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ikrefact?serverTimezone=UTC", username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //voor het ophalen van gegevens uit de database
    static ResultSet executeDatabaseQuery(String query) {
        try {
            prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //voor het verwijderen of updaten van gegevens uit de database
    static void executeDatabaseUpdate(String query) {
        try {
            prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void prepareStatement(String query) throws SQLException {
        DatabaseConnector.openDatabaseConnection();
        preparedStatement = databaseConnection.prepareStatement(query);
    }

    static void closeDatabaseConnection(ResultSet result) {
        try {
            databaseConnection.close();
            preparedStatement.close();
            result.close();
        } catch (SQLException ignored) {
        }
    }
}
