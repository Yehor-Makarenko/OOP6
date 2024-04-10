package server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.db.classes.Account;

public class DBAccountController {
  private static DBController dbController = DBController.getInstance(); 

  private DBAccountController() {

  }

  public static Account getAccountByCardId(int cardId) {
    Account account = new Account();
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT * FROM accounts WHERE car_id = ?");
      stmt.setInt(1, cardId);
      ResultSet res = stmt.executeQuery();

      if (res.next()) {
        account = new Account(res.getInt("balance"), res.getBoolean("is_blocked"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return account;
  }
}
