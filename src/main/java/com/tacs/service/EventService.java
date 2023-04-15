package com.tacs.service;

import com.google.inject.Inject;
import com.tacs.dto.EventDto;
import com.tacs.model.Event;
import com.tacs.model.User;
import com.tacs.repository.EventRepository;
import com.tacs.util.mapper.EventMapper;
import java.util.List;

public class EventService {
  private final EventRepository eventRepository;

  @Inject
  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public List<EventDto> getAllEvents() {
    return eventRepository.findAll().stream().map(EventMapper::toDto).toList();
  }

  public List<EventDto> getAllByIsActive(Boolean isActive) {
    return eventRepository.findAllByIsActive(isActive).stream().map(EventMapper::toDto).toList();
  }

  public EventDto getEventById(Long id) {
    return EventMapper.toDto(eventRepository.findById(id));
  }

  public void createEvent(EventDto eventDto) {
    var user = User.builder().id(eventDto.getOrganizerId()).build();
    var event = Event.builder()
        .organizer(user)
        .isActive(true)
        .build();
    eventRepository.save(event);
    eventDto.setId(event.getId());
    eventDto.setIsActive(true);
  }
}
