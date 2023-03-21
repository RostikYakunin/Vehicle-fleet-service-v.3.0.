package com.vehicle.api;

import com.vehicle.api.servises.DriverServiceI;
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
// 5. finished route dto, dto handler, service, controller|| TODO: test with repo query is fallen
// 6. TODO: finished transport dto, handler, service, controller||