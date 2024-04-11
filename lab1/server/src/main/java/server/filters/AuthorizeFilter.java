package server.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.JWTVerificationException;

import server.JWTService;
import server.db.classes.Client;
import server.servlets.dtos.UserJWT;

@WebFilter("/auth/*")
public class AuthorizeFilter extends HttpFilter {
  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    String token = req.getHeader("Authorization").split(" ")[1];
    try {
      UserJWT user = JWTService.verifyToken(token);
      req.setAttribute("email", user.getEmail());   
      chain.doFilter(req, res);   
    } catch (Exception e) {
      res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      res.getWriter().println("Error, unauthorized!");
      res.getWriter().close();      
    }    
  }
}