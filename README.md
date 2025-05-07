# Master_Microservices
- Project to learn and develop more about Microservices with Java, Spring Boot, Spring Cloud, 
Docker, Kubernetes, Helm, including Microservices Security

## DB Entities Structure
- Customer: customer_id (PK), name, email, mobile_number, created_at, created_by, updated_at, updated_by
- Accounts: account_number (PK), customer_id (FK), account_type, address, created_at, created_by, updated_at, updated_by

- Accounts has a foreign key to table Customer (customer_id)

### Entities
- Customer: Represents a customer in the system.
- Accounts: Represents an account associated with a customer.
- CustomerAccount: Represents the relationship between a customer and their accounts.
- @CreatedDate: Automatically sets the created_at field to the current date and time when a new entity is created.
- @LastModifiedDate: Automatically sets the updated_at field to the current date and time when an entity is updated.
- @CreatedBy: Automatically sets the created_by field to the user who created the entity.
- @LastModifiedBy: Automatically sets the updated_by field to the user who last modified the entity.


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
#### @EnableJpaAuditing:
- Enables JPA auditing, allowing the use of @CreatedDate, @LastModifiedDate, @CreatedBy, and @LastModifiedBy annotations.


## DTO(Data Transfer Object) Pattern
- A design pattern used to transfer data between different layers of an application.
- DTOs are used to encapsulate data and reduce the number of method calls, improving performance and maintainability.
- DTOs are typically used in conjunction with service classes and repositories to transfer data between the database and the presentation layer.
- 
#### DTOs in this project
- CustomerDTO is used to transfer data about the customer, including their name, email, and mobile number.
- AccountDTO is used to transfer data about the customer's accounts, including the account number, account type, and address.
- CustomerDTO uses AccountDTO so that it can transfer data about the customer and their accounts in a single object.


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
- deleteById() is a method provided by the JpaRepository interface to delete an entity by its ID.
- deleteByCustomerId() is a custom method defined in the repository interface to delete accounts by customer ID 
(requires @Modifying and @Transactional annotations).

##### @Transactional
- The @Transactional annotation is used to define the transaction boundaries for a method or class.
- It ensures that all database operations within the annotated method or class are executed within a single transaction.
- If any operation fails, the transaction is rolled back, ensuring data consistency.

##### @Modifying
- The @Modifying annotation is used to indicate that a method modifies the database (e.g., insert, update, delete).
- It is typically used in conjunction with the @Query annotation to define custom queries that modify the database.


## Exception Handling
- CustomerAlreadyExistsException: Thrown when a customer with the same email or same mobile number already exists in the database.

### Global Exception Handler
- @ControllerAdvice annotation to indicate that it is a global exception handler.
- With this annotation, we can handle exceptions globally across all controllers in the application.
- Every exception thrown by the controller will be handled by this class.
- @ExceptionHandler annotation to specify the method that will handle the exception.
- ResponseEntity class to return a custom error response with a status code and message.
- The handleCustomerAlreadyExistsException method handles the CustomerAlreadyExistsException and returns an exception with a custom error message.
- The handleResourceNotFoundException method handles the ResourceNotFoundException and returns a custom error message.
- The handleException method handles all other exceptions and returns a generic error message.


## Validations

### DTO Validations
- @NotEmpty: Ensures that the annotated field is not null or empty.
- @Email: Ensures that the annotated field is a valid email address.
- @Pattern: Ensures that the annotated field matches a specified regular expression pattern.
- @Size: Ensures that the annotated field has a specified size (minimum and maximum).
- CustomerDTO and AccountDTO classes use these annotations to validate the input data.

### Controller Validations
- @Validated: Used to trigger validation on the annotated class or method.
- @Validated annotation is used in the controller to validate the input data before processing it.
- @Valid: Used to trigger validation on the annotated method parameter.


## Audit Columns
- Audit columns are used to track the creation and modification of entities in the database.
- They are typically used to store metadata about the entity, such as the user who created or modified it and the date and time of the operation.
##### AccountsApplication 
- This class is annotated with @EnableJpaAuditing to enable JPA auditing.
##### BaseEntity
- This class contains the audit columns (created_at, created_by, updated_at, updated_by) 
and is annotated with @EntityListeners(AuditingEntityListener.class) to enable auditing.
- The @CreatedDate and @LastModifiedDate annotations are used to automatically set the created_at and updated_at fields.
- The @CreatedBy and @LastModifiedBy annotations are used to automatically set the created_by and updated_by fields.
##### AuditAwareImpl
- This class implements the AuditorAware interface to provide the current user for auditing purposes.
- It uses the SecurityContextHolder to retrieve the current user from the security context.