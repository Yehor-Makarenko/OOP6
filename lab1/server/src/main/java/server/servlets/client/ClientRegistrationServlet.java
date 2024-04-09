package server.servlets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.db.ClientController;
import server.db.DBController;
import server.db.classes.Client;

@WebServlet("/client/registration")
public class ClientRegistrationServlet extends HttpServlet {
  @Override  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");    
    String email = req.getParameter("email");    
    String password = req.getParameter("password");      
    Client client = new Client(username, email, password);
    ClientController cc = new ClientController(client);
    
    if (cc.hasClient()) {
      resp.setStatus(HttpServletResponse.SC_CONFLICT);
      return;
    }

    cc.addClient();
    String token = cc.getToken();

    PrintWriter writer = resp.getWriter();
    writer.println(token);    
    writer.close();
  }
}
