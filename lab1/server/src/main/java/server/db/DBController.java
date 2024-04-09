package server.db;

import java.sql.*;
import java.util.Properties;

import lombok.Getter;

public class DBController {
  private static DBController instance = null;
  @Getter private Connection connection = null;

  private DBController() {}

  public static DBController getInstance() {
    if (instance != null) {
      return instance;
    }

    instance = new DBController();
    instance.connect();
    return instance;
  }

  private void connect() {
    String url = "jdbc:postgresql://localhost:5432/payments";
    Properties props = new Properties();
    props.setProperty("user", "postgres");
    props.setProperty("password", "admin");    
    try {
      this.connection = DriverManager.getConnection(url, props);
      // Statement stmt = conn.createStatement();
      // ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE id<3");
      // while (result.next()) {
      //   String value = result.getString("name");
      //   System.out.println(value);
      // }
    } catch (SQLException e) {      
      e.printStackTrace();
    }
  }
}
