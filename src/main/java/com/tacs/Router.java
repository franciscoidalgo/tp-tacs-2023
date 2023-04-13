package com.tacs;

import com.google.inject.Injector;
import io.javalin.apibuilder.EndpointGroup;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Router implements EndpointGroup {
    private final Injector injector;

    @Override
    public void addEndpoints() {}
}
