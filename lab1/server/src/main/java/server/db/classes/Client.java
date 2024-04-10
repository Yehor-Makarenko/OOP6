package server.db.classes;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Client {
  private int id;
  private String name;
  private String email;  
}
