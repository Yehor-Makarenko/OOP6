package server.servlets.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import server.JWTService;
import server.db.controllers.user.DBAdminController;
import server.db.controllers.user.DBClientController;
import server.servlets.dtos.UserJWT;

@WebServlet("/admin/registration")
public class AdminRegistrationServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");   
    UserJWT userJWT = new UserJWT(email, "ADMIN");   
    DBAdminController adminController = DBAdminController.getInstance();
    
    if (adminController.hasUserWithEmail(email)) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"User already exists\"}");
      resp.getWriter().close();
      return;
    }

    adminController.addUser(username, email, password);
    String token = JWTService.generateJWT(userJWT); //make jwt user

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode jsonObject = mapper.createObjectNode();
    jsonObject.put("token", token);    
    
    resp.setContentType("application/json");
    resp.getWriter().write(mapper.writeValueAsString(jsonObject));   
  }
}
