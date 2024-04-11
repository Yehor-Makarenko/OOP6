package server;

import java.sql.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import server.db.classes.DBClient;
import server.servlets.dtos.UserJWT;

public class JWTService {
  private static Algorithm algorithm = Algorithm.HMAC256("aaa");
  private static int expirationTime = 3600000;
  private static String issuer = "payment-system";

  public static String generateJWT(UserJWT user) { // class for ClientJWT and mapstructaav
    String token = JWT.create()
    .withIssuer(issuer)    
    .withClaim("email", user.getEmail())
    .withClaim("role", user.getRole())
    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
    .sign(algorithm);

    return token;
  }

  public static UserJWT verifyToken(String token) throws JWTVerificationException {
    DecodedJWT jwt = JWT.require(algorithm)
      .withIssuer(issuer)
      .build()
      .verify(token);
    
    String email = jwt.getClaim("email").asString();
    String role = jwt.getClaim("role").asString();
    return new UserJWT(email, role);
  }
}
