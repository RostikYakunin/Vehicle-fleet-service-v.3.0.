package com.vehicle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}

// TODO: list of actions
// 1. class driverDto for add action with string qualification || +
// 2. handler for dto in driver service or separate class|| +
// 3. relationship between enum and driver @oneToOne or @oneToMany|| - don`t touch enum (delete table enum)
// 4. finish driver service and controller|| TODO: partly tested
// TODO: 5. finished route dto, dto handler, service, controller