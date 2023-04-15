package com.tacs.dao;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

  public int deleteById(I id, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();

    var transaction = session.beginTransaction();
    var hqlQuery = String.format("DELETE FROM %s WHERE id = :id", clazz.getName());
    var query = session.createQuery(hqlQuery, clazz);
    query.setParameter("id", id);
    var result = query.executeUpdate();
    transaction.commit();
    return result;
  }

  public int update(String hqlQuery, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    var result = query.executeUpdate();
    transaction.commit();
    return result;
  }

  public List<T> findAll(String hqlQuery, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    var result = query.list();
    transaction.commit();
    return result;
  }

  public List<T> findAll(String hqlQuery, List<QueryParam> params, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    setQueryParams(query, params);
    var result = query.list();
    transaction.commit();
    return result;
  }

  public List<T> findAllPaginated(String hqlQuery, Class<T> clazz, int startPosition,
                                  int maxResults) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    query.setFirstResult(startPosition);
    query.setMaxResults(maxResults);
    var result = query.list();
    transaction.commit();
    return result;
  }

  public List<T> findAllPaginated(String hqlQuery, List<QueryParam> params, Class<T> clazz,
                                  int startPosition, int maxResults) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    setQueryParams(query, params);
    query.setFirstResult(startPosition);
    query.setMaxResults(maxResults);
    var result = query.list();
    transaction.commit();
    return result;
  }

  public T find(String hqlQuery, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    var result = query.getSingleResultOrNull();
    transaction.commit();
    return result;
  }

  public T find(String hqlQuery, List<QueryParam> params, Class<T> clazz) {
    var session = sessionFactory.getCurrentSession();
    var transaction = session.beginTransaction();
    var query = session.createQuery(hqlQuery, clazz);
    setQueryParams(query, params);
    var result = query.getSingleResultOrNull();
    transaction.commit();
    return result;
  }

  private void setQueryParams(Query<T> query, List<QueryParam> params) {
    for (QueryParam param : params) {
      query.setParameter(param.getParamName(), param.getValue());
    }
  }
}
