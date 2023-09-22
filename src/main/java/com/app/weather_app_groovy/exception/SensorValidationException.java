package com.app.weather_app_groovy.exception;

public class SensorValidationException extends RuntimeException {
    public SensorValidationException(String message) {
        super(message);
    }
}
