package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.dao.QueryParam;
import com.tacs.model.DateOption;
import com.tacs.repository.DateOptionRepository;
import java.util.List;

public class DateOptionRepositoryImpl extends GenericCrudRepositoryImpl<DateOption, Long>
    implements DateOptionRepository {

  @Inject
  public DateOptionRepositoryImpl(Dao<DateOption, Long> dao) {
    super(dao, DateOption.class);
  }

  @Override
  public List<DateOption> findByEventId(Long eventId) {
    var queryParam = new QueryParam("eventId", eventId);
    return this.dao.findAll("select option from Event as event " +
        "inner join event.dateOptions as option " +
        "where event.id = :eventId", List.of(queryParam), DateOption.class);
  }
}
