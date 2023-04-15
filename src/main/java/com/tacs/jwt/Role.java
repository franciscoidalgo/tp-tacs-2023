package com.tacs.jwt;

import io.javalin.security.RouteRole;
import java.util.Arrays;
import lombok.Getter;

public enum Role implements RouteRole {
  ANYONE("anyone"), USER("user");

  @Getter
  private final String value;

  Role(String value) {
    this.value = value;
  }

  public static Role getRoleByValue(String value) {
    return Arrays.stream(values())
        .filter(role -> role.value.equals(value))
        .findAny().orElse(ANYONE);
  }

  public static Role getDefault() {
    return ANYONE;
  }
}
