package server;

import java.sql.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import server.db.classes.Client;

public class JWTService {
  private static Algorithm algorithm = Algorithm.HMAC256("aaa");
  private static int expirationTime = 3600000;
  private static String issuer = "payment-system";

  public static String generateJWT(Client client) { // class for ClientJWT and mapstructaav
    String token = JWT.create()
    .withIssuer(issuer)
    .withClaim("name", client.getName())
    .withClaim("email", client.getEmail())
    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
    .sign(algorithm);

    return token;
  }

  public static Client verifyToken(String token) {
    DecodedJWT jwt = JWT.require(algorithm)
      .withIssuer(issuer)
      .build()
      .verify(token);
    
    String name = jwt.getClaim("name").asString();
    String email = jwt.getClaim("email").asString();
    return new Client(name, email, email);
  }
}
