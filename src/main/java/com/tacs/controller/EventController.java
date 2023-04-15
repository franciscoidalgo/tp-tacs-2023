package com.tacs.controller;

import com.google.inject.Inject;
import com.tacs.dto.ApiExceptionDto;
import com.tacs.dto.EventDto;
import com.tacs.exception.ApiException;
import com.tacs.service.EventService;
import com.tacs.util.Constants;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EventController {
  private final EventService eventService;

  @Inject
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }


  public void createEvent(Context context) {
    EventDto event = context.bodyAsClass(EventDto.class);
    try {
      eventService.createEvent(event);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ApiException(Constants.PERSISTENCE_ERROR, e,
          HttpStatus.INTERNAL_SERVER_ERROR.getCode());
    }
    context.json(event).status(HttpStatus.CREATED);
  }

  public void updateEvent(Context context) {
  }

  public void deleteEvent(Context context) {
  }

  public void getEvent(Context context) {
    Long id = Long.valueOf(context.pathParam("id"));
    EventDto event = eventService.getEventById(id);
    context.json(event).status(HttpStatus.OK);
  }

  public void getAllEvents(Context context) {
    String isActiveParam = context.queryParam("is_active");
    if (isActiveParam == null) {
      List<EventDto> events = eventService.getAllEvents();
      context.json(events).status(HttpStatus.OK);
    } else {
      if (!Constants.BOOLEANS.contains(isActiveParam)) {
        ApiExceptionDto apiExceptionDto =
            new ApiExceptionDto(String.format(Constants.BAD_PARAM_MESSAGE_FORMAT, "is_active"),
                HttpStatus.BAD_GATEWAY.getCode());
        context.json(apiExceptionDto);
        context.status(HttpStatus.BAD_REQUEST);
      }
      Boolean isActive = Boolean.valueOf(isActiveParam);
      List<EventDto> events = eventService.getAllByIsActive(isActive);
      context.json(events).status(HttpStatus.OK);
    }

  }
}
