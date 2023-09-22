package com.app.weather_app_groovy.service;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.entity.Measurement;
import com.app.weather_app_groovy.entity.Sensor;
import com.app.weather_app_groovy.exception.SensorValidationException;
import com.app.weather_app_groovy.mapper.MeasurementMapper;
import com.app.weather_app_groovy.repository.MeasurementRepository;
import com.app.weather_app_groovy.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;
    private final SensorRepository sensorRepository;

    @Transactional
    public MeasurementDto addMeasurement(MeasurementDto measurementDto) {
        Measurement measurement = measurementMapper.toMeasurement(measurementDto);
        Optional<Sensor> foundSensor = sensorRepository.findSensorByName(measurement.getSensor().getName());

        if (foundSensor.isEmpty()) {
            log.error("Sensor with name {} not found", measurement.getSensor().getName());
            throw new SensorValidationException("Sensor with name " + measurement.getSensor().getName() + " not found.");
        }

        Sensor sensor = foundSensor.get();
        measurement.setSensor(sensor);

        Measurement savedMeasurement = measurementRepository.save(measurement);
        log.info("Measurement with id {} was registered", savedMeasurement.getId());

        return measurementMapper.toMeasurementDto(savedMeasurement);
    }
}
