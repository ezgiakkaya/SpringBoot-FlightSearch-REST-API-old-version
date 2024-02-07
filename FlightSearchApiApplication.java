package com.example.flightsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.flightsearchapi.config.DataInitializer;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })

public class FlightSearchApiApplication {

	public static void main(String[] args) {
		// DataInitializer init = new DataInitializer();
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}

}
