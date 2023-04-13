package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.model.DateOption;
import com.tacs.repository.DateOptionRepository;

public class DateOptionRepositoryImpl extends GenericCrudRepositoryImpl<DateOption, Long> implements DateOptionRepository {

    @Inject
    public DateOptionRepositoryImpl(Dao<DateOption, Long> dao) {
        super(dao, DateOption.class);
    }
}
