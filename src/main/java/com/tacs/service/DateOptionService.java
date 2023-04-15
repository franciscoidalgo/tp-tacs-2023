package com.tacs.service;

import com.google.inject.Inject;
import com.tacs.dto.DateOptionDto;
import com.tacs.repository.DateOptionRepository;
import com.tacs.util.mapper.DateOptionMapper;
import java.util.List;

public class DateOptionService {
  private final DateOptionRepository dateOptionRepository;

  @Inject
  public DateOptionService(DateOptionRepository dateOptionRepository) {
    this.dateOptionRepository = dateOptionRepository;
  }

  public List<DateOptionDto> getDateOptionsForEvent(Long eventId) {
    return dateOptionRepository.findByEventId(eventId).stream().map(DateOptionMapper::toDto).toList();
  }
}
