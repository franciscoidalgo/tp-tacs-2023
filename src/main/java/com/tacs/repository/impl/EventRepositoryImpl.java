package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.dao.QueryParam;
import com.tacs.model.Event;
import com.tacs.repository.EventRepository;
import java.util.List;

public class EventRepositoryImpl extends GenericCrudRepositoryImpl<Event, Long>
    implements EventRepository {

  @Inject
  public EventRepositoryImpl(Dao<Event, Long> dao) {
    super(dao, Event.class);
  }


  @Override
  public List<Event> findAll() {
    return this.dao.findAll("from Event", Event.class);
  }

  @Override
  public List<Event> findAllByIsActive(Boolean isActive) {
    var isActiveParam = new QueryParam("isActive", true);
    return this.dao.findAll("from Event where isActive = :isActive", List.of(isActiveParam),
        Event.class);
  }
}
