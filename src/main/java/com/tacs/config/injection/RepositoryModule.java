package com.tacs.config.injection;

import com.google.inject.AbstractModule;
import com.tacs.repository.DateOptionRepository;
import com.tacs.repository.EventRepository;
import com.tacs.repository.UserRepository;
import com.tacs.repository.impl.DateOptionRepositoryImpl;
import com.tacs.repository.impl.EventRepositoryImpl;
import com.tacs.repository.impl.UserRepositoryImpl;

public class RepositoryModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(DateOptionRepository.class).to(DateOptionRepositoryImpl.class).asEagerSingleton();
    bind(EventRepository.class).to(EventRepositoryImpl.class).asEagerSingleton();
    bind(UserRepository.class).to(UserRepositoryImpl.class).asEagerSingleton();
  }
}
