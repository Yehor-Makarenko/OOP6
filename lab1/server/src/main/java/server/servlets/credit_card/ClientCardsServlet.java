package server.servlets.credit_card;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.JWTService;
import server.db.DBAccountController;
import server.db.DBCardController;
import server.db.DBClientController;
import server.db.classes.Account;
import server.db.classes.Card;
import server.servlets.dtos.CardAccountInfo;

@WebServlet("/auth/card/")
public class ClientCardsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
    String email = (String) req.getAttribute("email");
    int clientId = DBClientController.getIdByEmail(email);
    ArrayList<Card> cards = DBCardController.getUserCards(clientId);
    ArrayList<CardAccountInfo> cardAccountInfos = new ArrayList<>();

    for (Card card: cards) {
      Account account = DBAccountController.getAccountByCardId(card.getId());      
      cardAccountInfos.add(new CardAccountInfo(card.getNumber(), card.getExpirationDate(), card.getCvv(), 
        account.getBalance(), account.isBlocked()));
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String resultJSON = objectMapper.writeValueAsString(cardAccountInfos);

    resp.setContentType("application/json");
    resp.getWriter().write(resultJSON);    
  }
}
