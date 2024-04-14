package server.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBPaymentController {
  private static DBController dbController = DBController.getInstance();

  public static void addPayment(int accountID, int amount, String description) {        
    Timestamp currenDate = new Timestamp(System.currentTimeMillis());
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("INSERT INTO payments (account_id, amount, payment_date, description) VALUES (?, ?, ?, ?)");
      stmt.setInt(1, accountID);
      stmt.setInt(2, amount);
      stmt.setTimestamp(3, currenDate);
      stmt.setString(4, description);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
