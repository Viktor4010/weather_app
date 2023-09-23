package com.app.weather_app_groovy.repository;

import com.app.weather_app_groovy.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(nativeQuery = true,
            value = "select * from measurements where :value is null or value > :value")
    List<Measurement> findAllWithValueFilter(@Param(value = "value") Double value);
}
