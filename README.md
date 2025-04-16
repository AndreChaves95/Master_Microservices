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


## REST API
### Controller
- The controller is responsible for handling HTTP requests and responses.
- @RestController annotation to indicate that it is a REST controller.
- @RequestMapping annotation to specify the base URL for the controller.
- produces attribute to specify the response type (application/json).

### Service
- The service layer contains the business logic of the application.
- It is responsible for processing requests from the controller and interacting with the repository layer.
- @Service annotation to indicate that it is a service class.

### Repository
- The repository layer is responsible for interacting with the database and for defining custom queries using JPQL or native SQL.
- It uses Spring Data JPA to perform CRUD operations on the database.
- @Repository annotation to indicate that it is a repository class.
- It extends the JpaRepository interface to provide basic CRUD operations.


## Exception Handling
- CustomerAlreadyExistsException: Thrown when a customer with the same email or same mobile number already exists in the database.

### Global Exception Handler
- @ControllerAdvice annotation to indicate that it is a global exception handler.
- With this annotation, we can handle exceptions globally across all controllers in the application.
- Every exception thrown by the controller will be handled by this class.
- @ExceptionHandler annotation to specify the method that will handle the exception.
- ResponseEntity class to return a custom error response with a status code and message.