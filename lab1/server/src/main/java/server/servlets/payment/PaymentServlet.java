package server.servlets.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("auth/payment")
public class PaymentServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int cardNumber = Integer.parseInt(req.getParameter("card_number"));
    int sum = Integer.parseInt(req.getParameter("sum"));
  }
}
