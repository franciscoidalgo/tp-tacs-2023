package com.tacs.util.mapper;

import com.tacs.dto.EventDto;
import com.tacs.model.Event;


public class EventMapper {
  private EventMapper() {
  }

  public static EventDto toDto(Event event) {
    return EventDto.builder()
        .id(event.getId())
        .organizerId(event.getOrganizer().getId())
        .organizerName(event.getOrganizer().getUsername())
        .isActive(event.getIsActive())
        .dateOptions(event.getDateOptions().stream().map(DateOptionMapper::toDto).toList())
        .chosenOption(
            event.getChosenOption() != null ? DateOptionMapper.toDto(event.getChosenOption()) :
                null)
        .build();
  }

}
