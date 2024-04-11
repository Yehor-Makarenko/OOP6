package server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.db.classes.DBAccount;

public class DBAccountController {
  private static DBController dbController = DBController.getInstance(); 

  private DBAccountController() {

  }

  public static DBAccount getAccountByCardId(int cardId) {
    DBAccount account = null;
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT * FROM accounts WHERE card_id = ?");
      stmt.setInt(1, cardId);
      ResultSet res = stmt.executeQuery();

      if (res.next()) {
        account = new DBAccount(res.getInt("balance"), res.getBoolean("is_blocked"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return account;
  }
}
