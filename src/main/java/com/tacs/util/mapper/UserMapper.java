package com.tacs.util.mapper;

import com.tacs.dto.UserDto;
import com.tacs.model.User;

public class UserMapper {
  private UserMapper() {
  }

  public static UserDto toDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .build();
  }
}
