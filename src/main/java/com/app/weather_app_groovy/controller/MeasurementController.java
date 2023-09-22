package com.app.weather_app_groovy.controller;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    public MeasurementDto addMeasurement(@RequestBody @Valid MeasurementDto measurementDto) {
        return measurementService.addMeasurement(measurementDto);
    }
}
