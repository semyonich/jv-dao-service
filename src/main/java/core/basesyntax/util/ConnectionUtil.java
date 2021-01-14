package core.basesyntax.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "testuser");
        dbProperties.put("password", "123456");
        String url = "jdbc:mysql://localhost:3306/taxi-service";
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to DB", e);
        }
    }
}
