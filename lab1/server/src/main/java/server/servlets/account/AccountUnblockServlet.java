package server.servlets.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.classes.DBUser;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBAccountUnblocksController;
import server.db.controllers.DBCardController;
import server.db.controllers.user.DBAdminController;

@WebServlet("/auth-admin/account/unblock")
public class AccountUnblockServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = (String) req.getAttribute("email");
    DBUser admin = DBAdminController.getInstance().getUserByEmail(email);
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));    
    DBCard card = DBCardController.getInstance().getCardByNumber(cardNumber);
    DBAccountController accountController = DBAccountController.getInstance();
    DBAccount dbAccount = accountController.getAccountByCardId(card.getId());    
    accountController.unblock(dbAccount.getId());
    DBAccountUnblocksController.getInstance().addUnblock(dbAccount.getId(), admin.getId()); // Add admin id
  }
}
