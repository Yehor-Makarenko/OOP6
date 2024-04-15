package server.db.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.db.classes.DBAccount;

public class DBAccountController extends DBController {
  public  DBAccount getAccountByCardId(int cardId) {
    DBAccount account = null;
    try {
      PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM accounts WHERE card_id = ?");
      stmt.setInt(1, cardId);
      ResultSet res = stmt.executeQuery();

      if (res.next()) {
        account = new DBAccount(res.getInt("account_id"), res.getInt("balance"), res.getBoolean("is_blocked"));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return account;
  }

  public  void setBalance(int accountID, int newBalance) {
    try {
      PreparedStatement stmt = this.connection.prepareStatement("UPDATE accounts SET balance = ? WHERE account_id = ?");
      stmt.setInt(1, newBalance);
      stmt.setInt(2, accountID);
      stmt.executeUpdate();      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public  void block(int accountID) {
    try {
      PreparedStatement stmt = this.connection.prepareStatement("UPDATE accounts SET is_blocked = ? WHERE account_id = ?");
      stmt.setBoolean(1, true);
      stmt.setInt(2, accountID);
      stmt.executeUpdate();      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public  void unblock(int accountID) {
    try {
      PreparedStatement stmt = this.connection.prepareStatement("UPDATE accounts SET is_blocked = ? WHERE account_id = ?");
      stmt.setBoolean(1, false);
      stmt.setInt(2, accountID);
      stmt.executeUpdate();      
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
