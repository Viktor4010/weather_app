package com.app.weather_app_groovy.service;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.entity.Measurement;
import com.app.weather_app_groovy.entity.Sensor;
import com.app.weather_app_groovy.exception.SensorValidationException;
import com.app.weather_app_groovy.mapper.MeasurementMapper;
import com.app.weather_app_groovy.repository.MeasurementRepository;
import com.app.weather_app_groovy.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {
    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private MeasurementMapper measurementMapper;

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private MeasurementService measurementService;

    @Test
    public void testAddMeasurementSensorNotFound() {
        MeasurementDto measurementDto = new MeasurementDto();
        Measurement measurement = new Measurement();

        when(measurementMapper.toMeasurement(measurementDto)).thenReturn(measurement);

        Sensor sensor = new Sensor();
        sensor.setName("sensorName");

        measurement.setSensor(sensor);

        when(sensorRepository.findSensorByName(sensor.getName())).thenReturn(Optional.empty());

        SensorValidationException sensorValidationException = assertThrows(SensorValidationException.class,
                () -> measurementService.addMeasurement(measurementDto));

        assertEquals("Sensor with name sensorName not found.", sensorValidationException.getMessage());
        verify(sensorRepository, times(1)).findSensorByName(sensor.getName());
        verify(measurementRepository, never()).save(measurement);
    }

    @Test
    public void testGetAllMeasurements() {
        Double value = 10.0;
        List<Measurement> measurements = new ArrayList<>();
        when(measurementRepository.findAllWithValueFilter(value)).thenReturn(measurements);

        List<MeasurementDto> result = measurementService.getAllMeasurements(value);

        assertNotNull(result);
        assertEquals(measurements.size(), result.size());
        verify(measurementRepository, times(1)).findAllWithValueFilter(value);
    }

    @Test
    public void testGetRainyDaysCount() {
        Long rainyDaysCount = 5L;
        when(measurementRepository.getRainyDaysCount()).thenReturn(rainyDaysCount);

        Long result = measurementService.getRainyDaysCount();

        assertNotNull(result);
        assertEquals(rainyDaysCount, result);
        verify(measurementRepository, times(1)).getRainyDaysCount();
    }
}