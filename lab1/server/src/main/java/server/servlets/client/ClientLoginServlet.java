package server.servlets.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.JWTService;
import server.db.classes.DBUser;
import server.db.controllers.user.DBClientController;
import server.servlets.dtos.UserJWT;

@WebServlet("/client/login")
public class ClientLoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {    
    String email = req.getParameter("email");
    String password = req.getParameter("password");  
    UserJWT userJWT = new UserJWT(email, "CLIENT");
    DBClientController clientController = new DBClientController();

    if (!clientController.hasUserWithEmail(email)) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"No user with such email\"}");
      resp.getWriter().close();
      return;
    } else if (!clientController.checkPassword(email, password)) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"Wrong password\"}");
      resp.getWriter().close();
    }

    String token = JWTService.generateJWT(userJWT); //make jwt user

    PrintWriter writer = resp.getWriter();
    writer.println(token);    
    writer.close();
  }
}
