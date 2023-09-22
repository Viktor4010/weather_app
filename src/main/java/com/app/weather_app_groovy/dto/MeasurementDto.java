package com.app.weather_app_groovy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasurementDto {
    @NotNull(message = "Value should not be empty")
    private Double value;
    @NotNull(message = "raining should not be empty")
    private boolean raining;
    @NotNull(message = "Sensor name should not be empty")
    private SensorDto sensor;
}
