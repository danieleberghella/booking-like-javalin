package com.berghella.daniele.utility.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private final String urlDB = "jdbc:postgresql://localhost:5432/booking-like";
    private final String userDB = "postgres";
    private final String pswDB = "AdminAdmin";

    private DatabaseConnection() {
        try {
//            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlDB, userDB, pswDB);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}

