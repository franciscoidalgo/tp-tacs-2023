package com.tacs.config;

import java.util.Arrays;
import lombok.Getter;

public enum ScopesEnum {
  LOCAL("local"), PROD("prod");

  @Getter
  private final String value;

  ScopesEnum(String value) {
    this.value = value;
  }

  public static ScopesEnum getScopeByValue(String value) {
    return Arrays.stream(values())
        .filter(scope -> scope.value.equals(value))
        .findAny().orElse(LOCAL);
  }
}
