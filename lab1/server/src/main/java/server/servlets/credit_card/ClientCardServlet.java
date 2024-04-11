package server.servlets.credit_card;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.db.DBAccountController;
import server.db.DBCardController;
import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.servlets.dtos.CardAccountInfo;

@WebServlet("/auth/card/:number")
public class ClientCardServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumer = Integer.parseInt(req.getParameter("number"));
    DBCard dbCard = DBCardController.getCardByNumber(cardNumer);
    DBAccount dbAccount = DBAccountController.getAccountByCardId(dbCard.getId());
    CardAccountInfo cardAccountInfo = new CardAccountInfo(dbCard.getNumber(), dbCard.getExpirationDate(), 
      dbCard.getCvv(), dbAccount.getBalance(), dbAccount.isBlocked());

    ObjectMapper objectMapper = new ObjectMapper();
    String resultJSON = objectMapper.writeValueAsString(cardAccountInfo);

    resp.setContentType("application/json");
    resp.getWriter().write(resultJSON);   
  }
}
