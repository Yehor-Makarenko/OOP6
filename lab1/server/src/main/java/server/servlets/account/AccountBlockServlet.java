package server.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.DBAccountBlocksController;
import server.db.DBAccountController;
import server.db.DBCardController;
import server.db.classes.DBAccount;
import server.db.classes.DBCard;

@WebServlet("/auth/account/block")
public class AccountBlockServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));
    String blockReason = req.getParameter("reason");
    DBCard card = DBCardController.getCardByNumber(cardNumber);
    DBAccount dbAccount = DBAccountController.getAccountByCardId(card.getId());
    String role = (String) req.getAttribute("role");
    if (role.equals("ADMIN")) {
      DBAccountBlocksController.block(dbAccount.getId(), cardNumber, blockReason); // Add admin id
    } else {
      DBAccountBlocksController.block(dbAccount.getId(), blockReason);
    }
    DBAccountController.block(dbAccount.getId());
  }
}
