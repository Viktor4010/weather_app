package com.app.weather_app_groovy.repository;

import com.app.weather_app_groovy.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query(nativeQuery = true, value = """
    select * from sensor where name = :name
    """)
    Optional<Sensor> findSensorByName(@Param("name") String name);
}
