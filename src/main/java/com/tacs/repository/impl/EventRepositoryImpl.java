package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.model.Event;
import com.tacs.repository.EventRepository;

public class EventRepositoryImpl extends GenericCrudRepositoryImpl<Event, Long> implements EventRepository {

    @Inject
    public EventRepositoryImpl(Dao<Event, Long> dao) {
        super(dao, Event.class);
    }
}
