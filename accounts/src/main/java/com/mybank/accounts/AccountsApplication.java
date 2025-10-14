package com.mybank.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // Enables JPA Auditing, which allows to automatically populate auditing fields (like createdAt, updatedAt) on entities
@OpenAPIDefinition(info = @Info(title = "Accounts microservice REST API documentation",
		description = "REST API for managing accounts",
		version = "1.0",
		contact = @Contact(name = "André Chaves",
				email = "andrefmoreirachaves@hotmail.com"
		)),
		externalDocs = @ExternalDocumentation(
				description = "GitHub Repository",
				url = "https://github.com/AndreChaves95/Master_Microservices"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
