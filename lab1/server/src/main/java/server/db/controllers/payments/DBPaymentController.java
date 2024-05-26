package server.db.controllers.payments;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import server.db.classes.DBCard;
import server.db.classes.DBPayment;
import server.db.controllers.DBController;

public class DBPaymentController extends DBController {
  private static DBPaymentController instance = null;  

  private DBPaymentController() {}

  public static DBPaymentController getInstance() {
    if (instance == null) {
      instance = new DBPaymentController();
    }

    return instance;
  }

  public void addPayment(int accountID, int amount, String description) {        
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

  public ArrayList<DBPayment> getPaymentsByAccountId(int accountID) {
    ArrayList<DBPayment> payments = new ArrayList<>();

    try {
      PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM payments WHERE account_id = ?");
      stmt.setInt(1, accountID);
      ResultSet res = stmt.executeQuery();

      while (res.next()) {
        DBPayment payment = new DBPayment(res.getInt("amount"), res.getDate("payment_date"), res.getString("description"));
        payments.add(payment);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return payments;
  }
}
