package oop.lab;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/events")
public class SSExample extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/event-stream");
    response.setCharacterEncoding("UTF-8");

    PrintWriter out = response.getWriter();    
    for (int i = 0; i < 3; i++) {
      out.print("retry: 0\ndata: Connection..\nid:" + i + "\n\n");
      out.flush();

      try {
        Thread.sleep(1000); // Задержка между событиями
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    out.close();
  }
}
