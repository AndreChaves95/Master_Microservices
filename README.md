# Master_Microservices
- Project to learn and develop more about Microservices with Java, Spring Boot, Spring Cloud, 
Docker, Kubernetes, Helm, including Microservices Security

## DB Entities Structure
- Customer: customer_id (PK), name, email, mobile_number, created_at, created_by, updated_at, updated_by
- Accounts: account_number (PK), customer_id (FK), account_type, address, created_at, created_by, updated_at, updated_by

- Accounts has a foreign key to table Customer (customer_id)


## Spring Boot Annotations
#### @SpringBootApplication: 
- Enables auto-configuration and beans scanning.
#### @RestController: 
- Marks the class as a REST controller, allowing it to handle HTTP requests.
#### @MappedSuperclass: 
- Indicates that the class is a superclass for JPA entities, but should not be mapped to a database table.
- Also requires that his attributes are inherited by subclasses.
#### @Entity: 
- Marks the class as a JPA entity, allowing it to be mapped to a database table.
#### @Repository: 
- Indicates that the class is a Spring Data repository, allowing it to perform CRUD operations on the database.


## DTO(Data Transfer Object) Pattern
- A design pattern used to transfer data between different layers of an application.
- DTOs are used to encapsulate data and reduce the number of method calls, improving performance and maintainability.
- DTOs are typically used in conjunction with service classes and repositories to transfer data between the database and the presentation layer.