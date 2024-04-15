package server.db.controllers;

import java.sql.Connection;

import server.db.DBConnector;

public abstract class DBController {
  protected Connection connection = DBConnector.getConnection();  
}
