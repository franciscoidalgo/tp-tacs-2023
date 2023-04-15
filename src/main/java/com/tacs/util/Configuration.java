package com.tacs.util;

import com.tacs.exception.ConfigurationLoadingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
  private final Properties properties;

  public Configuration(String resourceName) {
    var classLoader = Thread.currentThread().getContextClassLoader();
    properties = new Properties();
    try (InputStream resourceStream = classLoader.getResourceAsStream(resourceName)) {
      properties.load(resourceStream);
    } catch (IOException e) {
      throw new ConfigurationLoadingException(e);
    }
  }

  public String getString(String key) {
    return properties.getProperty(key);
  }

  public Integer getInt(String key) {
    return Integer.parseInt(getString(key));
  }

  public Double getDouble(String key) {
    return Double.parseDouble(getString(key));
  }

  public Float getFloat(String key) {
    return Float.parseFloat(getString(key));
  }
}
