package server.db;

import java.sql.*;
import java.util.Properties;

import lombok.Getter;

public class DBConnector {  
  private static Connection connection = null;

  private DBConnector() {}

  public static Connection getConnection() {
    if (connection != null) {
      return connection;
    }
    
    DBConnector.connect();
    return connection;
  }

  private static void connect() {    
    String url = "jdbc:postgresql://localhost:5432/payments";
    Properties props = new Properties();
    props.setProperty("user", "postgres");
    props.setProperty("password", "admin");    
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection(url, props);      
    } catch (SQLException | ClassNotFoundException e) {      
      e.printStackTrace();
    }
  }
}
