package com.tacs.config;

import io.javalin.openapi.plugin.OpenApiPluginConfiguration;

public class OpenApiConfig {
  private OpenApiConfig() {
  }

  public static OpenApiPluginConfiguration getConfig() {
    return new OpenApiPluginConfiguration()
        .withDefinitionConfiguration((ver, def) -> {
          def.withOpenApiInfo((openApiInfo -> {
            openApiInfo.setTitle("Javalin Template");
          }));
        });
  }
}
