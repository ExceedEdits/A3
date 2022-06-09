package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WiringSystem {
    static String urlBD = "jdbc:mysql://localhost:3306/Valorant_bd";
    static String username = "root"; // root
    static String password = "090403Gi"; // senha da intalação

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        connection = DriverManager.getConnection(urlBD, username, password);

        return connection;
    }
}
