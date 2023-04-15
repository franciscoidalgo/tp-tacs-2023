package com.tacs.repository;

import com.tacs.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);

  boolean existByUsernameAndPassword(String username, String password);
}
