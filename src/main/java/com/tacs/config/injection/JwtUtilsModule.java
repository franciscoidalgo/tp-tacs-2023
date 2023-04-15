package com.tacs.config.injection;

import com.google.inject.AbstractModule;
import com.tacs.util.JwtUtils;

public class JwtUtilsModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(JwtUtils.class).asEagerSingleton();
  }
}
