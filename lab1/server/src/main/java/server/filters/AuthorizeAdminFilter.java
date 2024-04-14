package server.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.JWTService;
import server.servlets.dtos.UserJWT;

@WebFilter("/auth-admin/*")
public class AuthorizeAdminFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {      
        try {
        String token = req.getHeader("Authorization").split(" ")[1];
        UserJWT user = JWTService.verifyToken(token);

        if (!user.getRole().equals("admin")) {
          throw new Exception();
        }

        req.setAttribute("role", user.getRole());      
        req.setAttribute("email", user.getEmail());   
        chain.doFilter(req, res);   
      } catch (Exception e) {
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.getWriter().println("Error, not admin!");
        res.getWriter().close();      
      }   
  }
}
