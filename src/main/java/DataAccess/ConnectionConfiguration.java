package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Yasi on 11/14/2016.
 */

public class ConnectionConfiguration {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/customers?useUnicode=true&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASS = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException exp) {
            System.out.println("Error in Connection Configuration-1: " + exp.getMessage());
            exp.printStackTrace();
        } catch (SQLException exp) {
            System.out.println("Error in Connection Configuration-2: " + exp.getMessage());
        }
        return connection;
    }
}
