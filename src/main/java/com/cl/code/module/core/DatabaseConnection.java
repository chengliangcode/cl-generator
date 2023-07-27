package com.cl.code;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author chengliang
 * @since 1.0.0
 */
public class DatabaseConnection {

    public static Connection getConnection(String url, String username, String password) {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(url, "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
