package server.servlets.credit_card;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.JWTService;
import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.classes.DBUser;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBCardController;
import server.db.controllers.user.DBClientController;
import server.mapstruct.DBCardCardInfoMapper;
// import server.mapstruct.DBCardCardInfoMapper;
import server.servlets.dtos.CardAccountInfo;
import server.servlets.dtos.CardInfo;

@WebServlet("/auth/card")
public class ClientCardsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
    String email = (String) req.getAttribute("email");
    DBUser client = DBClientController.getInstance().getUserByEmail(email);
    ArrayList<DBCard> cards = DBCardController.getInstance().getClientCards(client.getId());
    ArrayList<CardInfo> cardInfos = new ArrayList<>();

    DBCardCardInfoMapper mapper = Mappers.getMapper(DBCardCardInfoMapper.class);
    for (DBCard card: cards) {      
      CardInfo cardInfo = mapper.dbCardToCardInfo(card);
      cardInfos.add(cardInfo);
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String resultJSON = objectMapper.writeValueAsString(cardInfos);

    resp.setContentType("application/json");
    resp.getWriter().write(resultJSON);    
  }
}
