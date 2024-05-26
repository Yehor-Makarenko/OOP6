package server.db.controllers.user;

public class DBClientController extends DBUserController {  
  private static DBClientController instance = null;

  private DBClientController() {
    super("clients", "client_id");
  }

  public static DBClientController getInstance() {
    if (instance == null) {
      instance = new DBClientController();
    }
    return instance;
  }
}
