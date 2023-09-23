package com.app.weather_app_groovy.service;

import com.app.weather_app_groovy.dto.SensorDto;
import com.app.weather_app_groovy.entity.Sensor;
import com.app.weather_app_groovy.exception.SensorValidationException;
import com.app.weather_app_groovy.mapper.SensorMapper;
import com.app.weather_app_groovy.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {
    @Mock
    private SensorRepository sensorRepository;
    @Mock
    private SensorMapper sensorMapper;
    @InjectMocks
    private SensorService sensorService;

    @Test
    public void testRegisterValidSensor() {
        SensorDto sensorDto = SensorDto.builder()
                .name("sensor")
                .build();
        Sensor sensor = Sensor.builder()
                .id(1L)
                .name("sensor")
                .build();

        when(sensorMapper.toSensor(sensorDto)).thenReturn(sensor);
        when(sensorMapper.toSensorDto(sensor)).thenReturn(sensorDto);

        SensorDto result = sensorService.register(sensorDto);

        assertNotNull(result);
        verify(sensorRepository, times(1)).save(sensor);
    }

    @Test
    public void testRegisterSensorAlreadyExists() {
        SensorDto sensorDto = SensorDto.builder().name("sensor").build();
        Sensor sensor = Sensor.builder().name("sensor").build();
        when(sensorMapper.toSensor(sensorDto)).thenReturn(sensor);
        when(sensorRepository.findSensorByName(sensor.getName())).thenReturn(java.util.Optional.of(sensor));

        SensorValidationException sensorValidationException = assertThrows(SensorValidationException.class,
                () -> sensorService.register(sensorDto));

        assertEquals("Sensor with name sensor already exists", sensorValidationException.getMessage());
        verify(sensorRepository, never()).save(sensor);
    }
}