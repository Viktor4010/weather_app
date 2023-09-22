package com.app.weather_app_groovy.service;

import com.app.weather_app_groovy.dto.SensorDto;
import com.app.weather_app_groovy.entity.Sensor;
import com.app.weather_app_groovy.exception.SensorValidationException;
import com.app.weather_app_groovy.mapper.SensorMapper;
import com.app.weather_app_groovy.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SensorService {
    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @Transactional
    public SensorDto register(SensorDto sensorDto) {
        Sensor sensor = sensorMapper.toSensor(sensorDto);
        sensorAlreadyExistsValidation(sensor);

        log.info("Sensor with name {} was registered", sensor.getName());
        sensorRepository.save(sensor);
        return sensorMapper.toSensorDto(sensor);
    }

    private void sensorAlreadyExistsValidation(Sensor sensor) {
        sensorRepository.findSensorByName(sensor.getName()).ifPresent(s -> {
                    log.error("Sensor with name {} already exists", sensor.getName());
                    throw new SensorValidationException("Sensor with name " + sensor.getName() + " already exists");
                });
    }
}
