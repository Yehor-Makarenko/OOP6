package server.servlets.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.JWTService;
import server.db.DBClientController;
import server.db.classes.Client;

@WebServlet("/client/login")
public class ClientLoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Client client = new Client(req);    

    if (!DBClientController.hasClientWithEmail(client.getEmail())) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"No user with such email\"}");
      resp.getWriter().close();
      return;
    } else if (!DBClientController.checkPassword(client.getEmail(), client.getPassword())) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"Wrong password\"}");
      resp.getWriter().close();
    }

    String token = JWTService.generateJWT(client);

    PrintWriter writer = resp.getWriter();
    writer.println(token);    
    writer.close();
  }
}
