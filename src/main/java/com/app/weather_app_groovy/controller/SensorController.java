package com.app.weather_app_groovy.controller;

import com.app.weather_app_groovy.dto.SensorDto;
import com.app.weather_app_groovy.service.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/register")
    public SensorDto registerNewSensor(@RequestBody @Valid SensorDto sensorDto) {
        return sensorService.register(sensorDto);
    }
}
