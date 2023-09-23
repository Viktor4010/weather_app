package com.app.weather_app_groovy.mapper;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.entity.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MeasurementMapper {
    MeasurementDto toMeasurementDto(Measurement measurement);

    Measurement toMeasurement(MeasurementDto measurementDto);

    default List<MeasurementDto> toListMeasurementDto(List<Measurement> all) {
        return all.stream()
                .map(this::toMeasurementDto)
                .toList();
    }

    default List<Measurement> toListMeasurement(List<MeasurementDto> all) {
        return all.stream()
                .map(this::toMeasurement)
                .toList();
    }
}
