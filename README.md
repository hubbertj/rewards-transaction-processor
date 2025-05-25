
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