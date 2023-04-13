package com.tacs.exception;

public class ConfigurationLoadingException extends RuntimeException{
    public ConfigurationLoadingException(Throwable e) {
        super("Error loading configuration file", e);
    }
}
