package server.servlets.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJWT {
  private String email;
  private String role;
}
