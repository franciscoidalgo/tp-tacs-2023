package com.tacs.dao;

import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.io.Serializable;

public class Dao<T, I extends Serializable> {
    private final SessionFactory sessionFactory;

    @Inject
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T findById(I id, Class<T> clazz) {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.beginTransaction();
        var result = session.find(clazz, id);
        transaction.commit();
        return result;
    }

    public void save(T entity) {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    public void update(T entity) {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    public void delete(T entity) {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    public Integer deleteById(I id, String idColumn, Class<T> clazz) {
        var session = sessionFactory.getCurrentSession();

        var transaction = session.beginTransaction();
        var hqlQuery = String.format("DELETE FROM %s " +
                "WHERE %s = %s",
                clazz.getName(), idColumn, id.toString());
        var query = session.createQuery(hqlQuery, clazz);
        var result = query.executeUpdate();
        transaction.commit();
        return result;
    }

    public Integer executeQuery(String hqlQuery, Class<T> clazz) {
        var session = sessionFactory.getCurrentSession();
        var transaction = session.beginTransaction();
        var query = session.createQuery(hqlQuery, clazz);
        var result = query.executeUpdate();
        transaction.commit();
        return result;
    }
}
