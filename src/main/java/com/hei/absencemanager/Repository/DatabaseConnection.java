package com.hei.absencemanager.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/absencemanager",
                "postgres",
                "PATAPOUFFE");
        return connection;
    }
}
