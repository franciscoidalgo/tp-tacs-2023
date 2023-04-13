package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.model.User;
import com.tacs.repository.UserRepository;

public class UserRepositoryImpl extends GenericCrudRepositoryImpl<User, Long> implements UserRepository {

    @Inject
    public UserRepositoryImpl(Dao<User, Long> dao) {
        super(dao, User.class);
    }
}
