# Spring Boot demo

## Projects

- PostgresSQL with flyway (student-folder)

# PostgreSQL WSL 

- `sudo service postgresql start`
- `sudo service postgresql status`
- `sudo service postgresql stop`

![alt text](src/main/java/spring/demo/img/postgresql.png)

# Run project with remote JVM Debug
- mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8001"
