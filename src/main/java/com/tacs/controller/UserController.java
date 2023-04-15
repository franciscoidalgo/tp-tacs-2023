package com.tacs.controller;

import com.google.inject.Inject;
import com.tacs.dto.ApiExceptionDto;
import com.tacs.dto.UserDto;
import com.tacs.jwt.Role;
import com.tacs.service.UserService;
import com.tacs.util.Constants;
import com.tacs.util.JwtUtils;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;

@Slf4j
public class UserController {
  private final UserService userService;
  private final JwtUtils jwtUtils;

  @Inject
  public UserController(UserService userService, JwtUtils jwtUtils) {
    this.userService = userService;
    this.jwtUtils = jwtUtils;
  }

  public void createUser(Context context) throws NoSuchAlgorithmException, InvalidKeySpecException {
    UserDto user = context.bodyAsClass(UserDto.class);
    userService.createUser(user);
    context.json(user).status(HttpStatus.CREATED);
  }

  public void login(Context context)
      throws NoSuchAlgorithmException, InvalidKeySpecException, DecoderException {
    UserDto user = context.bodyAsClass(UserDto.class);
    if (userService.login(user)) {
      context.result(jwtUtils.generateJwt(user.getUsername(), Role.USER.getValue()))
          .status(HttpStatus.OK);
    } else {
      context.json(
          new ApiExceptionDto(Constants.COULD_NOT_LOGIN, HttpStatus.UNAUTHORIZED.getCode()));
    }
  }
}
