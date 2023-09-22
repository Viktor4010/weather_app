package com.app.weather_app_groovy.mapper;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.entity.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MeasurementMapper {
    MeasurementDto toMeasurementDto(Measurement measurement);

    Measurement toMeasurement(MeasurementDto measurementDto);
}
