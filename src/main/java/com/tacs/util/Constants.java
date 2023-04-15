package com.tacs.util;

import java.util.List;

public class Constants {
  // Booleans
  public static final String FALSE = "false";
  public static final String TRUE = "true";
  public static final List<String> BOOLEANS = List.of(FALSE, TRUE);
  // Messages
  public static final String FAILED_AUTH_ERROR = "Failed to authenticate";
  public static final String BAD_PARAM_MESSAGE_FORMAT = "Invalid param: %s";
  public static final String PERSISTENCE_ERROR = "Error persisting entity";
  // Jwt
  public static final String ROLE_CLAIM = "role_claim";
  // UserController
  public static final String USER_CREATION_ERROR = "Error creating user";
  public static final String COULD_NOT_LOGIN = "Wrong or non existent credentials";

  private Constants() {
  }
}
