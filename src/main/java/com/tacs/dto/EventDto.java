package com.tacs.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
  Long eventId;
  Long organizerId;
  String organizerName;
  Boolean isActive;
  List<DateOptionDto> dateOptions;
  DateOptionDto chosenOption;
}
