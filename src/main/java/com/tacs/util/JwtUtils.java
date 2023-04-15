package com.tacs.util;

import com.google.inject.Inject;
import io.javalin.http.Context;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Optional;

public class JwtUtils {
  private static final String AUTHORIZATION = "Authorization";
  private static final String BEARER = "Bearer";
  private final JwtParserBuilder jwtParserBuilder;
  private final JwtBuilder jwtBuilder;
  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


  @Inject
  public JwtUtils(JwtParserBuilder jwtParserBuilder, JwtBuilder jwtBuilder) {
    this.jwtParserBuilder = jwtParserBuilder;
    this.jwtBuilder = jwtBuilder;
  }

  public Optional<String> getJwtFromHeader(Context context) {
    return Optional.ofNullable(context.header(AUTHORIZATION))
        .flatMap(header -> {
          String[] split = header.split(" ");
          if (split.length != 2 || !BEARER.equals(split[0])) {
            return Optional.empty();
          }

          return Optional.of(split[1]);
        });
  }

  public void addJwtToHeaders(Context context, String jwt) {
    String header = String.format("Bearer %s", jwt);
    context.header(AUTHORIZATION, header);
  }

  public Claims parseClaims(String jwt) {
    return jwtParserBuilder.setSigningKey(key).build().parseClaimsJws(jwt).getBody();
  }

  public String generateJwt(String subject, String role) {
    return jwtBuilder.setSubject(subject).claim(Constants.ROLE_CLAIM, role).signWith(key).compact();
  }
}
