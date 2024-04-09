package server.db.classes;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Client {
  private String name;
  private String email;
  private String password;

  public Client(HttpServletRequest req) {
    this.name = req.getParameter("username");    
    this.email = req.getParameter("email");    
    this.password = req.getParameter("password");  
  }
}
