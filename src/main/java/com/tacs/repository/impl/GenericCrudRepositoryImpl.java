package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.repository.CrudRepository;

import java.io.Serializable;

public class GenericCrudRepositoryImpl<T, I extends Serializable> implements CrudRepository<T, I> {
    private final Dao<T, I> dao;
    private final Class<T> clazz;

    @Inject
    public GenericCrudRepositoryImpl(Dao<T, I> dao, Class<T> clazz) {
        this.dao = dao;
        this.clazz = clazz;
    }

    @Override
    public T findById(I id) {
        return dao.findById(id, clazz);
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }
}
