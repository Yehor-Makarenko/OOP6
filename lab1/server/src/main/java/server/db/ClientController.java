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
public class ClientController {
  @Getter 
  @NonNull private Client client;
  private DBController dbController = DBController.getInstance();  

  public void addClient() {
    String hashedPassword = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
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
      Statement stmt = dbController.getConnection().createStatement();
      ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM clients WHERE email = " + client.getEmail());      
      count = res.getInt(1);      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return count > 0;
  }

  public String getToken() {
    String token = JWT.create()
    .withIssuer("payment-system")
    .withClaim("name", client.getName())
    .withClaim("email", client.getEmail())
    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
    .sign(Algorithm.HMAC256("SecretKey"));

    return token;
  }
}
