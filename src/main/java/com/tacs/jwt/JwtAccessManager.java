package com.tacs.jwt;

import com.google.inject.Inject;
import com.tacs.dto.ApiExceptionDto;
import com.tacs.exception.ApiException;
import com.tacs.util.Constants;
import com.tacs.util.JwtUtils;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.security.AccessManager;
import io.javalin.security.RouteRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class JwtAccessManager implements AccessManager {
  private final JwtUtils jwtUtils;

  @Inject
  public JwtAccessManager(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  public void manage(@NotNull Handler handler, @NotNull Context context,
                     @NotNull Set<? extends RouteRole> permittedRoles) throws Exception {
    Role role = getRole(context);
    if (permittedRoles.isEmpty() || permittedRoles.contains(Role.getDefault()) ||
        permittedRoles.contains(role)) {
      handler.handle(context);
    } else {
      context.status(HttpStatus.UNAUTHORIZED)
          .json(new ApiExceptionDto("User not authorized", HttpStatus.UNAUTHORIZED.getCode()));
    }
  }

  private Role getRole(Context context) {
    Optional<String> jwt = jwtUtils.getJwtFromHeader(context);
    if (jwt.isEmpty()) {
      return Role.getDefault();
    }
    try {
      Claims claims = jwtUtils.parseClaims(jwt.get());
      return Role.getRoleByValue(claims.get(Constants.ROLE_CLAIM, String.class));
    } catch (JwtException e) {
      throw new ApiException(Constants.FAILED_AUTH_ERROR, e, HttpStatus.UNAUTHORIZED.getCode());
    }
  }
}
