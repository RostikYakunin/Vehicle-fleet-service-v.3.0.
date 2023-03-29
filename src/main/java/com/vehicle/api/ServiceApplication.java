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

// 3. relationship between enum and driver @oneToOne or @oneToMany|| - don`t touch enum (delete table enum)
// 4. finish driver service and controller|| TODO: partly tested
// 5. finished route dto, dto handler, service, controller|| TODO: test with repo query is fallen
// 6. finished transport dto, handler, service, controller|| TODO: see controller`s todo and fix
// 7. TODO: watch video for relationship between tables in db (Spring data)
// 8. TODO: create todolist project
// 9. TODO: to think about how to create a DTO for returned value