package server.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBAccountUnblocksController;
import server.db.controllers.DBCardController;

public class AccountUnblockServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String role = (String) req.getAttribute("role");
    if (!role.equals("ADMIN")) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"Only admin can unblock cards\"}");
      resp.getWriter().close();
    }

    int cardNumber = Integer.parseInt(req.getParameter("card_number"));    
    DBCard card = new DBCardController().getCardByNumber(cardNumber);
    DBAccountController accountController = new DBAccountController();
    DBAccount dbAccount = accountController.getAccountByCardId(card.getId());    
    accountController.unblock(dbAccount.getId());
    new DBAccountUnblocksController().unblock(dbAccount.getId(), cardNumber); // Add admin id
  }
}
