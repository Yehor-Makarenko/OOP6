package server.db.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccountBlocksController extends DBController {  
  public void addBlock(int accountID, int adminId, String reason) {
    Date currentDate = new Date(System.currentTimeMillis());
    try {
      PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO account_blocks (account_id, admin_id, blocking_date, reason) VALUES (?, ?, ?, ?)");
      stmt.setInt(1, accountID);
      stmt.setInt(2, adminId);
      stmt.setDate(3, currentDate);
      stmt.setString(4, reason);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void addBlock(int accountID, String reason) {
    Date currentDate = new Date(System.currentTimeMillis());
    try {
      PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO account_blocks (account_id, blocking_date, reason) VALUES (?, ?, ?)");
      stmt.setInt(1, accountID);      
      stmt.setDate(2, currentDate);
      stmt.setString(3, reason);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
