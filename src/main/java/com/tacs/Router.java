package com.tacs;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.google.inject.Injector;
import com.tacs.controller.DateOptionController;
import com.tacs.controller.EventController;
import com.tacs.controller.UserController;
import com.tacs.jwt.Role;
import io.javalin.apibuilder.EndpointGroup;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Router implements EndpointGroup {
  private final Injector injector;

  @Override
  public void addEndpoints() {
    var eventController = injector.getInstance(EventController.class);
    var userController = injector.getInstance(UserController.class);
    var dateOptionController = injector.getInstance(DateOptionController.class);

    path("/api/v1/", () -> {
      path("/events", () -> {
        get(eventController::getAllEvents, Role.USER);
        get("/{id}", eventController::getEvent, Role.USER);
        post(eventController::createEvent, Role.USER, Role.ANYONE);
        path("/{eventId}/date_options", () -> {
          get(dateOptionController::getDateOptionsForEvent, Role.USER, Role.ANYONE);
        });
      });
      path("/users", () -> {
        post(userController::createUser);
      });
      post("/login", userController::login);
    });
  }
}
