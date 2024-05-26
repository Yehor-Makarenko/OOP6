package server.servlets.client;

import java.io.BufferedReader;
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

@WebServlet("/client/registration")
public class ClientRegistrationServlet extends HttpServlet {
  @Override  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");   
    UserJWT userJWT = new UserJWT(email, "CLIENT");   
    DBClientController clientController = DBClientController.getInstance();
    
    if (clientController.hasUserWithEmail(email)) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"User already exists\"}");
      resp.getWriter().close();
      return;
    }

    clientController.addUser(username, email, password);
    String token = JWTService.generateJWT(userJWT);   

    PrintWriter writer = resp.getWriter();
    writer.println(token);    
    writer.close();
  }
}
