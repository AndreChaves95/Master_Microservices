# Master_Microservices
- Project to learn and develop more about Microservices with Java, Spring Boot, Spring Cloud, 
Docker, Kubernetes, Helm, Microservices Security

### Entities Structure
- Customer: customer_id (PK), name, email, mobile_number, created_at, created_by, updated_at, updated_by
- Accounts: customer_id (FK), account_number (PK), account_type, address, created_at, created_by, updated_at, updated_by

- Accounts has a foreign key to table Customer (customer_id)
