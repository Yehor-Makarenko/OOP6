package server.db.controllers.user;

public class DBAdminController extends DBUserController {
  private static DBAdminController instance = null;

  private DBAdminController() {
    super("admins", "admin_id");
  }

  public static DBAdminController getInstance() {
    if (instance == null) {
      instance = new DBAdminController();
    }
    return instance;
  }
}
