package org.example.mysqlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerMysql {
    private static ConnectionManagerMysql instance;
    private Connection conn;

    private static final String URL = "jdbc:mysql://localhost:4000/integrador1?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "agus";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private ConnectionManagerMysql() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static ConnectionManagerMysql getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerMysql();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
