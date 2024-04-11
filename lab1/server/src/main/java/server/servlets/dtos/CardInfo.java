package server.servlets.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardInfo {
  private int number;
  private Date expirationDate;
  private int cvv;
}
