package server.db.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccountUnblocksController extends DBController {
  public void addUnblock(int accountID, int adminId) {
    Date currentDate = new Date(System.currentTimeMillis());
    try {
      PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO account_blocks (account_id, admin_id, unblocking_date) VALUES (?, ?, ?)");
      stmt.setInt(1, accountID);
      stmt.setInt(2, adminId);
      stmt.setDate(3, currentDate);      
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
