package server.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.controllers.DBAccountBlocksController;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBCardController;

@WebServlet("/auth/account/block")
public class AccountBlockServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));
    String blockReason = req.getParameter("reason");
    DBCard card = new DBCardController().getCardByNumber(cardNumber);
    DBAccount dbAccount = new DBAccountController().getAccountByCardId(card.getId());
    String role = (String) req.getAttribute("role");
    if (role.equals("ADMIN")) {
      new DBAccountBlocksController().block(dbAccount.getId(), cardNumber, blockReason); // Add admin id
    } else {
      new DBAccountBlocksController().block(dbAccount.getId(), blockReason);
    }
    new DBAccountController().block(dbAccount.getId());
  }
}
