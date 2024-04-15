package server.db.controllers.user;

public class DBClientController extends DBUserController {  
  public DBClientController() {
    super("clients", "client_id");
  }
}
