package server.servlets.payment;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;

import com.fasterxml.jackson.databind.ObjectMapper;

import server.db.classes.DBAccount;
import server.db.classes.DBCard;
import server.db.classes.DBPayment;
import server.db.controllers.DBAccountController;
import server.db.controllers.DBCardController;
import server.db.controllers.payments.DBPaymentController;
import server.mapstruct.DBCardCardInfoMapper;
import server.mapstruct.DBPaymentPaymentInfoMapper;
import server.servlets.dtos.CardInfo;
import server.servlets.dtos.PaymentInfo;

@WebServlet("/payments")
public class PaymentsServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
    int cardNumber = Integer.parseInt(req.getParameter("number"));
    DBCard card = DBCardController.getInstance().getCardByNumber(cardNumber);
    DBAccount account = DBAccountController.getInstance().getAccountByCardId(card.getId());
    ArrayList<DBPayment> payments = DBPaymentController.getInstance().getPaymentsByAccountId(account.getId());
    ArrayList<PaymentInfo> paymentInfos = new ArrayList<>();

    DBPaymentPaymentInfoMapper mapper = Mappers.getMapper(DBPaymentPaymentInfoMapper.class);
    for (DBPayment payment: payments) {      
      PaymentInfo cardInfo = mapper.dbPaymentToPaymentInfo(payment);
      paymentInfos.add(cardInfo);
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String resultJSON = objectMapper.writeValueAsString(paymentInfos);

    resp.setContentType("application/json");
    resp.getWriter().write(resultJSON);    
  }
}
