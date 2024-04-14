package server.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAccountBlocksController {
  private static DBController dbController = DBController.getInstance(); 

  private DBAccountBlocksController() {

  }

  public static void block(int accountID, int adminId, String reason) {
    Date currentDate = new Date(System.currentTimeMillis());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO account_blocks (account_id, admin_id, blocking_date, reason) VALUES (?, ?, ?, ?)");
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

  public static void block(int accountID, String reason) {
    Date currentDate = new Date(System.currentTimeMillis());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO account_blocks (account_id, blocking_date, reason) VALUES (?, ?, ?)");
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
