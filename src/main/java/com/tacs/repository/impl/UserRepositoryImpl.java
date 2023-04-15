package com.tacs.repository.impl;

import com.google.inject.Inject;
import com.tacs.dao.Dao;
import com.tacs.dao.QueryParam;
import com.tacs.model.User;
import com.tacs.repository.UserRepository;
import java.util.List;

public class UserRepositoryImpl extends GenericCrudRepositoryImpl<User, Long>
    implements UserRepository {

  @Inject
  public UserRepositoryImpl(Dao<User, Long> dao) {
    super(dao, User.class);
  }

  @Override
  public User findByUsername(String username) {
    var queryParam = new QueryParam("username", username);
    return this.dao.find("from User where username = :username", List.of(queryParam), User.class);
  }

  @Override
  public boolean existByUsernameAndPassword(String username, String password) {
    var usernameParam = new QueryParam("username", username);
    var passwordParam = new QueryParam("password", password);
    return this.dao.find("from User where username = :username and password = :password",
        List.of(usernameParam, passwordParam), User.class) != null;
  }
}
