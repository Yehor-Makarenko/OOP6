package server.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.DBAccountController;
import server.db.DBAccountUnblocksController;
import server.db.DBCardController;
import server.db.classes.DBAccount;
import server.db.classes.DBCard;

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
    DBCard card = DBCardController.getCardByNumber(cardNumber);
    DBAccount dbAccount = DBAccountController.getAccountByCardId(card.getId());    
    DBAccountController.unblock(dbAccount.getId());
    DBAccountUnblocksController.unblock(dbAccount.getId(), cardNumber); // Add admin id
  }
}
