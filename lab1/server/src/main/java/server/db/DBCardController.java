package server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import server.db.classes.DBCard;

public class DBCardController {
  private static DBController dbController = DBController.getInstance();  

  private DBCardController() {

  }

  public static ArrayList<DBCard> getClientCards(int userId) {
    ArrayList<DBCard> cards = new ArrayList<>();
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT * FROM credit_cards WHERE client_id = ?");
      stmt.setInt(1, userId);
      ResultSet res = stmt.executeQuery();

      while (res.next()) {
        DBCard card = new DBCard(res.getInt("card_id"), res.getInt("card_number"), res.getDate("expiration_date"), res.getInt("cvv"));
        cards.add(card);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return cards;
  }

  public static DBCard getCardByNumber(int number) {
    DBCard card = null;
    try {
      PreparedStatement stmt = dbController.getConnection().prepareStatement("SELECT * FROM credit_cards WHERE card_number = ?");
      stmt.setInt(1, number);
      ResultSet res = stmt.executeQuery();

      if (res.next()) {
        card = new DBCard(res.getInt("card_id"), res.getInt("card_number"), res.getDate("expiration_date"), res.getInt("cvv"));        
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return card;
  }
}
