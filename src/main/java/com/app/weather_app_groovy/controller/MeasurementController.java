package com.app.weather_app_groovy.controller;

import com.app.weather_app_groovy.dto.MeasurementDto;
import com.app.weather_app_groovy.service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements(@RequestParam(required = false) Double value) {
        return measurementService.getAllMeasurements(value);
    }

    @PostMapping("/add")
    public MeasurementDto addMeasurement(@RequestBody @Valid MeasurementDto measurementDto) {
        return measurementService.addMeasurement(measurementDto);
    }
}
