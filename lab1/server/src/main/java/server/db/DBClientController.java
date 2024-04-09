package server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import server.db.classes.Client;

public class DBClientController {  
  private static DBController dbController = DBController.getInstance();  

  private DBClientController() {

  }

  public static int getIdByEmail(String email) {
    int id = -1;
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT * FORM clients WHERE email = ?");
      stmt.setString(1, email);
      ResultSet res = stmt.executeQuery();
      if (res.next()) {
        id = res.getInt("client_id");
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return id;
  }

  public static void addClient(String name, String email, String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO clients (name, email, password) VALUES (?, ?, ?)");
      stmt.setString(1, name);
      stmt.setString(2, email);
      stmt.setString(3, hashedPassword);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static boolean hasClientWithEmail(String email) {
    int count = 0;
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT COUNT(*) FROM clients WHERE email = ?");
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

  public static boolean checkPassword(String email, String password) {
    boolean isPasswordMatch = false;
    PreparedStatement stmt;
    try {
      stmt = dbController.getConnection().prepareStatement("SELECT * FROM clients WHERE email = ?");
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
