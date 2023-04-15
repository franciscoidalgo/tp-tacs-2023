package com.tacs.repository;

import com.tacs.model.Event;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
  List<Event> findAll();

  List<Event> findAllByIsActive(Boolean isActive);
}
