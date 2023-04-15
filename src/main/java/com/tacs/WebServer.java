package com.tacs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tacs.config.ExceptionHandler;
import com.tacs.config.OpenApiConfig;
import com.tacs.config.ScopesEnum;
import com.tacs.config.injection.ConfigurationModule;
import com.tacs.config.injection.JsonMapperModule;
import com.tacs.config.injection.JwtUtilsModule;
import com.tacs.config.injection.RepositoryModule;
import com.tacs.config.injection.SessionFactoryModule;
import com.tacs.exception.ApiException;
import com.tacs.jwt.JwtAccessManager;
import com.tacs.util.ScopeUtils;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebServer {

  private final Injector injector = Guice.createInjector(
      new ConfigurationModule(),
      new JsonMapperModule(),
      new JwtUtilsModule(),
      new RepositoryModule(),
      new SessionFactoryModule()
  );

  private final Javalin app = Javalin.create(config -> {
    config.accessManager(injector.getInstance(JwtAccessManager.class));
    config.jsonMapper(injector.getInstance(JsonMapper.class));
    config.plugins.register(new OpenApiPlugin(OpenApiConfig.getConfig()));
    config.plugins.register(new SwaggerPlugin());
  });

  private final ScopesEnum scope = ScopeUtils.getScope();

  public void start() {
    log.info(String.format("Scope set to %s", scope));
    app.routes(new Router(injector));
    configureExceptions();
    app.start(8080);
  }


  private void configureExceptions() {
    ExceptionHandler exceptionHandler = new ExceptionHandler();
    app.exception(ApiException.class, exceptionHandler::handleApiException);
  }
}
