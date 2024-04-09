package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/payments";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "admin");    
        try {
          Connection conn = DriverManager.getConnection(url, props);
          // Statement stmt = conn.createStatement();
          // ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE id<3");
          // while (result.next()) {
          //   String value = result.getString("name");
          //   System.out.println(value);
          // }
          System.out.println("Hi!");
        } catch (SQLException e) {      
          e.printStackTrace();
        }

    }
}