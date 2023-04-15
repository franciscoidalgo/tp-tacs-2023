package com.tacs.util.mapper;

import com.tacs.dto.DateOptionDto;
import com.tacs.model.DateOption;

public class DateOptionMapper {
  private DateOptionMapper() {
  }

  public static DateOptionDto toDto(DateOption dateOption) {
    return DateOptionDto.builder()
        .id(dateOption.getId())
        .dateTime(dateOption.getDateTime())
        .votedBy(dateOption.getVotedBy().stream().map(UserMapper::toDto).toList())
        .build();
  }
}
