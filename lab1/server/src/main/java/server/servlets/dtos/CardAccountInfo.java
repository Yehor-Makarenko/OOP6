package server.servlets.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardAccountInfo {
  private int cardNumber;
  private Date expirationDate;
  private int cvv;
  private int balance;
  private boolean isBlocked;  
}
