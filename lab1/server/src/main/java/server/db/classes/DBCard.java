package server.db.classes;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DBCard {
  private int id;
  private int number;
  private Date expirationDate;
  private int cvv;
}