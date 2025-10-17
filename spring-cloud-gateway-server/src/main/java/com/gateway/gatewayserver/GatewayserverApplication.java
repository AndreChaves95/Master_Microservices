package com.gateway.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

// To use Gateway server, we need to add the dependency spring-cloud-starter-gateway in the pom.xml file
// We also need to add Server Discover and Registry by adding the annotation @EnableEurekaClient to register the gateway server with
// Eureka Server. But in the latest version of Spring Boot, it is not mandatory to add this annotation. Just adding the dependency
// spring-cloud-starter-netflix-eureka-client is enough to register the gateway server with Eureka
// We can configure the routes in application.yml file, or we can create a bean of type RouteLocator
// In the below code, we are creating a bean of type RouteLocator to configure the routes
// We are using the RouteLocatorBuilder to create the routes
// We are using the load balancer (lb) to route the requests to the respective microservices
// The load balancer will use the service name registered with Eureka Server to route the requests
// We are also adding a custom response header "X-Response-Time" to the response
@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

    @Bean
    public RouteLocator createCustomRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/gateway/accounts/**")                                                                                     // matching the path pattern
                        .filters( f -> f.rewritePath("/gateway/accounts/(?<segment>.*)","/${segment}")   // rewriting the path before forwarding the request
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))                         // adding a custom response header
                        .uri("lb://ACCOUNTS"))                                                                                            // lb -> load balancer
                .route(p -> p
                        .path("/gateway/loans/**")
                        .filters( f -> f.rewritePath("/gateway/loans/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))
                .route(p -> p
                        .path("/gateway/cards/**")
                        .filters( f -> f.rewritePath("/gateway/cards/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS")).build();
    }


}
