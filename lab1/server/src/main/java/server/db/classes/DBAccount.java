package server.db.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DBAccount {
  private int id;
  private int balance;
  private boolean isBlocked;
}
