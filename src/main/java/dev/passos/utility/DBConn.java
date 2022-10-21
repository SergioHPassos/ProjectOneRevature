package dev.passos.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton class
public class DBConn {
    public static Connection connection;

    public static Connection getConnection() {
        try {
            // creat connection object, make connection with postgres db, return connection object
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1005");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
