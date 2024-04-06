package oop.lab;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
  public static void main(String[] args) {
    String url = "jdbc:postgresql://localhost:5432/test_db";
    Properties props = new Properties();
    props.setProperty("user", "postgres");
    props.setProperty("password", "admin");
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, props);
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE id<3");
      while (result.next()) {
        String value = result.getString("name");
        System.out.println(value);
      }
    } catch (SQLException e) {      
      e.printStackTrace();
    }
  }
}
