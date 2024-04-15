package server.db.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBPaymentController extends DBController {
  public  void addPayment(int accountID, int amount, String description) {        
    Timestamp currentDate = new Timestamp(System.currentTimeMillis());
    try {
      PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO payments (account_id, amount, payment_date, description) VALUES (?, ?, ?, ?)");
      stmt.setInt(1, accountID);
      stmt.setInt(2, amount);
      stmt.setTimestamp(3, currentDate);
      stmt.setString(4, description);
      stmt.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
