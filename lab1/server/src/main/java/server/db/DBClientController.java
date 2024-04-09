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

@RequiredArgsConstructor
public class DBClientController {
  @Getter 
  @NonNull private Client client;
  private DBController dbController = DBController.getInstance();  

  public void addClient() {
    String hashedPassword = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO clients (name, email, password) VALUES (?, ?, ?)");
      stmt.setString(1, client.getName());
      stmt.setString(2, client.getEmail());
      stmt.setString(3, hashedPassword);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public boolean hasClient() {
    int count = 0;
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT COUNT(*) FROM clients WHERE email = ?");
      stmt.setString(1, client.getEmail());
      ResultSet res = stmt.executeQuery();   
      res.next();   
      count = res.getInt(1);      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return count > 0;
  }
}
