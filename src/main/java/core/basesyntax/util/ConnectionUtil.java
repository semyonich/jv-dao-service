package core.basesyntax.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "testuser");
        dbProperties.put("password", "123456");
        String url = "jdbc:mysql://localhost:3306/taxi-service";
        return DriverManager.getConnection(url, dbProperties);
    }
}
