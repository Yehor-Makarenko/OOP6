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
import server.db.DBClientController;
import server.db.classes.Client;

@WebServlet("/client/registration")
public class ClientRegistrationServlet extends HttpServlet {
  @Override  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    Client client = new Client(username, email, password);       
    
    if (DBClientController.hasClientWithEmail(client.getEmail())) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      resp.setContentType("application/json");
      resp.getWriter().write("{\"error\": \"User already exists\"}");
      resp.getWriter().close();
      return;
    }

    DBClientController.addClient(client.getName(), client.getEmail(), client.getPassword());
    String token = JWTService.generateJWT(client);
    // try {
    //   JWTService.verifyToken("dfgsdgfd"); 
    // } catch (Exception e) {
    //   resp.setStatus(HttpServletResponse.SC_CONFLICT);
    //   resp.setContentType("application/json");
    //   resp.getWriter().write("{\"error\": \"Wrong token\"}");
    //   resp.getWriter().close();
    //   return;
    // }    

    PrintWriter writer = resp.getWriter();
    writer.println(token);    
    writer.close();
  }
}
