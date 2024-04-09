package server.servlets.credit_card;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.JWTService;
import server.db.DBCardController;
import server.db.DBClientController;
import server.db.classes.Card;

@WebServlet("/authorized/card/")
public class ClientCardsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String token = req.getHeader("Authorization").split(" ")[1];
    String email = JWTService.verifyToken(token).getEmail();
    int clientId = DBClientController.getIdByEmail(email);
    ArrayList<Card> cards = DBCardController.getUserCards(clientId);

    resp.getWriter().write("All ok!");
    resp.getWriter().close();
  }
}
