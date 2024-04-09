package server.db.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Client {
  private String name;
  private String email;
  private String password;
}
