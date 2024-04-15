package server.db.controllers.user;

public class DBAdminController extends DBUserController {
  public DBAdminController() {
    super("admins", "admin_id");
  }
}
