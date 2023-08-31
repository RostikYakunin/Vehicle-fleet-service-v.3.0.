# Vehicle-fleet-service-v.3.0.
Web App for vehicle fleet service.

## How it work
Web App dividies into layers which interacts according to the scheme "controller > service > repository".

- repository - saves info about vehicles, drivers and routes, has methods for CRUD operations and work with services. To save information is used MySQL data base;
- service - contains business logic, gets info from repositories and works with console;
- controller - provides endpoints for working with the application.

## Patterns

- Singleton;
- Repository (DAO);
- Controller;
- Service.

## Technologies and libraries

- Logging;
- JUnit & Mockito;
- Hibernate;
- Spring Boot (+ Spring DATA);
- Maven;
- Lombok;
- MySQL.
