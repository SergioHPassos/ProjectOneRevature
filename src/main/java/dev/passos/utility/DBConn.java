package dev.passos.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton class
public class DBConn {
    public static Connection connection;

    public static Connection getConnection() {
//        String url = System.getenv("POSTGRES_SQL_DB");
//        String username = System.getenv("DB_USERNAME");
//        String password = System.getenv("PASSWORD");
        try {
            // create connection object, make connection with postgres db, return connection object
//            connection = DriverManager.getConnection(url, username, password);
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres?user=postgres&password=password");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
