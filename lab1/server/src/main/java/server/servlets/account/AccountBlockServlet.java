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
import server.db.controllers.DBAccountBlocksController;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBCardController;
import server.db.controllers.user.DBAdminController;

@WebServlet("/auth/account/block")
public class AccountBlockServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));
    String blockReason = req.getParameter("reason");
    DBCard card = DBCardController.getInstance().getCardByNumber(cardNumber);
    DBAccount dbAccount = DBAccountController.getInstance().getAccountByCardId(card.getId());
    String role = (String) req.getAttribute("role");    
    if (role.equals("ADMIN")) {
      String email = (String) req.getAttribute("email");
      DBUser admin = DBAdminController.getInstance().getUserByEmail(email);
      DBAccountBlocksController.getInstance().addBlock(dbAccount.getId(), admin.getId(), blockReason);
    } else {
      DBAccountBlocksController.getInstance().addBlock(dbAccount.getId(), blockReason);
    }
    DBAccountController.getInstance().block(dbAccount.getId());
  }
}
