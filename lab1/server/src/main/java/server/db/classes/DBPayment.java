package server.db.classes;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DBPayment {
  private int amount;
  private Date paymentDate;
  private String description;
}
