package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER  = "org.postgresql.Driver";
    private static final String URL     = "jdbc:postgresql://localhost:5432/poo_exercicios";
    private static final String USUARIO = "postgres";
    private static final String SENHA   = "postgres";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado: " + e.getMessage(), e);
        }
    }
}
