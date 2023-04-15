package com.tacs.controller;

import com.google.inject.Inject;
import com.tacs.dto.DateOptionDto;
import com.tacs.service.DateOptionService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;

public class DateOptionController {
  private final DateOptionService dateOptionService;

  @Inject
  public DateOptionController(DateOptionService dateOptionService) {
    this.dateOptionService = dateOptionService;
  }

  public void getDateOptionsForEvent(Context context) {
    Long eventId = Long.parseLong(context.pathParam("eventId"));
    List<DateOptionDto> dateOptions = dateOptionService.getDateOptionsForEvent(eventId);
    context.json(dateOptions).status(HttpStatus.OK);
  }

}
