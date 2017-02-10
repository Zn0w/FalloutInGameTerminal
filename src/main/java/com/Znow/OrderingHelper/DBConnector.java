package com.Znow.OrderingHelper;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 08.02.2017.
 */
public class DBConnector {

    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/ordhelp_schema";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public DBConnector() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
