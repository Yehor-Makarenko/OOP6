package server.db.controllers.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import server.db.classes.DBUser;
import server.db.controllers.DBController;

public abstract class DBUserController extends DBController {
  private String tableName;

  public DBUserController(String tableName) {
    this.tableName = tableName;
  }

  public  DBUser getClientByEmail(String email) { // Make getClientByEmail
    DBUser client = null;
    try {
      PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM " + this.tableName + " WHERE email = ?");      
      stmt.setString(1, email);
      ResultSet res = stmt.executeQuery();
      if (res.next()) {
        client = new DBUser(res.getInt("client_id"), res.getString("name"), res.getString("email"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return client;
  }

  public  void addClient(String name, String email, String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    try {
      PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO " + this.tableName + " (name, email, password) VALUES (?, ?, ?)");      
      stmt.setString(1, name);
      stmt.setString(2, email);
      stmt.setString(3, hashedPassword);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public  boolean hasClientWithEmail(String email) {
    int count = 0;
    try {
      PreparedStatement stmt = this.connection.prepareStatement("SELECT COUNT(*) FROM " + this.tableName + " WHERE email = ?");      
      stmt.setString(1, email);
      ResultSet res = stmt.executeQuery();   
      res.next();   
      count = res.getInt(1);      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return count > 0;
  }

  public  boolean checkPassword(String email, String password) {
    boolean isPasswordMatch = false;
    PreparedStatement stmt;
    try {
      stmt = this.connection.prepareStatement("SELECT * FROM " + this.tableName + " WHERE email = ?");      
      stmt.setString(1, email); 
      ResultSet res = stmt.executeQuery();   
      res.next();
      String hashedPassword = res.getString("password");      

      isPasswordMatch = BCrypt.checkpw(password, hashedPassword);
    } catch (SQLException e) {      
      e.printStackTrace();
    }

    return isPasswordMatch;
  }
}
