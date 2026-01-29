package com.visitor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // PLACEHOLDERS - PLEASE UPDATE THESE WITH YOUR ACTUAL CREDENTIALS
    private static final String URL = "jdbc:mysql://localhost:3306/visitordb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "vinay"; // Update this!

    private static Connection connection = null;

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to database successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Please add the connector jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to database. Check URL, User, and Password.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
