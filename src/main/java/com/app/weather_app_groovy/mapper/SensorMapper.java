package com.app.weather_app_groovy.mapper;

import com.app.weather_app_groovy.dto.SensorDto;
import com.app.weather_app_groovy.entity.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SensorMapper {
    SensorDto toSensorDto(Sensor sensor);

    Sensor toSensor(SensorDto sensorDto);
}
