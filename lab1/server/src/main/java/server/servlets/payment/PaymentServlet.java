package server.servlets.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBCardController;
import server.db.controllers.payments.DBPaymentController;

@WebServlet("/auth/payment")
public class PaymentServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));
    int amount = Integer.parseInt(req.getParameter("amount"));    
    String description = req.getParameter("description");
    DBAccountController accountController = DBAccountController.getInstance();
    DBCard card = DBCardController.getInstance().getCardByNumber(cardNumber);
    DBAccount account = accountController.getAccountByCardId(card.getId());
    if (account.getBalance() - amount < 0) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"No enough money on account\"}");
      resp.getWriter().close();
      return;
    }
    if (account.isBlocked()) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"Card is blocked\"}");
      resp.getWriter().close();
      return;
    }

    DBPaymentController.getInstance().addPayment(account.getId(), amount, description);;
    accountController.setBalance(account.getId(), account.getBalance() - amount);
  }
}
