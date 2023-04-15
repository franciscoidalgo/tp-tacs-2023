package com.tacs.config.injection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.javalin.json.JavalinJackson;
import io.javalin.json.JsonMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.jackson.io.JacksonSerializer;

public class JsonMapperModule extends AbstractModule {

  private final ObjectMapper objectMapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
      .setSerializationInclusion(JsonInclude.Include.NON_NULL);

  @Provides
  public JsonMapper provideJsonMapper() {
    return new JavalinJackson(objectMapper);
  }

  @Provides
  public JwtBuilder provideJwtBuilder() {
    return Jwts.builder()
        .serializeToJsonWith(new JacksonSerializer<>(objectMapper));
  }

  @Provides
  public JwtParserBuilder provideJwtParserBuilder() {
    return Jwts.parserBuilder()
        .deserializeJsonWith(new JacksonDeserializer<>(objectMapper));
  }

}
