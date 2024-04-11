package server.db.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class DBAccount {
  private int balance;
  private boolean isBlocked;
}
