package server.servlets.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardAccountInfo {
  private int cardNumber;
  private Date expirationDate;
  private int cvv;
  private int balance;
  private boolean isBlocked;  
}
