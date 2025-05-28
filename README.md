# Retailer Rewards Program
This project implements a Retailer Rewards Program using Spring Boot. It calculates reward points for customers based on their purchase transactions over a three-month period. The program awards points as follows:
- 2 points for every dollar spent over $100 in each transaction.
- 1 point for every dollar spent between $50 and $100 in each transaction.
- No points for transactions below $50.
- The project includes a RESTful endpoint to retrieve the reward points earned by each customer per month and in total.
- The project uses an in-memory H2 database for storing transaction data and provides a Swagger UI for easy API documentation and testing.
- The project is built using Java 17 and Maven.
- The project structure follows standard Spring Boot conventions.
- The project includes unit tests to ensure the correctness of the reward points calculation logic.
- The project is designed to be easily extendable for future enhancements, such as adding more complex reward rules or integrating with external systems.
- The project is open-source and available on GitHub for contributions and improvements.
- The project is structured to follow best practices in software development, including separation of concerns, dependency injection, and use of design patterns where applicable.
- The project is designed to be easily deployable and runnable locally using Maven commands.
- The project includes comprehensive documentation in the README file to guide users through setup, usage, and testing.
- The project is designed to be easily maintainable, with clear code structure and comments explaining the logic.
- The project is designed to be scalable, allowing for future enhancements such as adding more complex reward rules or integrating with external systems.
- The project is designed to be easily deployable and runnable locally using Maven commands.
- The project includes comprehensive documentation in the README file to guide users through setup, usage, and testing.
- The project is designed to be easily maintainable, with clear code structure and comments explaining the logic.
- The project is designed to be scalable, allowing for future enhancements such as adding more complex reward rules or integrating with external systems.


[//]: # (Requirments)
Java 17 or higher

[//]: # (Build Tool)
Maven 3.6.3 or higher

[//]: # (Run the application)
```bash 
mvn clean install
mvn spring-boot:run
```

[//]: # (Access the H2 Database Console)
Access H2 Database Console:
```bash 
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.h2.console.enabled=true
```
Access H2 Console:
```bash 
http://localhost:8080/h2-console
```
Username: `sa`
Password: (leave blank) 

[//]: # (Access the Swagger UI)
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--springdoc.api-docs.enabled=true
```

[//]: # (Access the application)



[//]: # (Access the application)
http://localhost:8080/api/v1/hello


[//]: # (Access the H2 Database Console)
[//]: # (Connect to Swagger UI)
http://localhost:8080/swagger-ui/index.html#/