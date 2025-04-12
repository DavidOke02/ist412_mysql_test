package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static String user = "root";
    private static String password = "java"; //Replace with your SQL db password
    private static String url = "jdbc:mysql://localhost:3306/login_schema"; //Make sure you make the schema in MySQL first

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("MySQL driver could not be found", e);
            }
        }
        return connection;
}}
