# Projects Info

- Language: Java 21
- BuildTool: Maven
- Framework: Quarkus
- Type: RestAPI
- Database: Postgres
- Docker for containerization
- Kubernetes for container orchestration
- OS: Linux


## Projects

- PostgresSQL with flyway (student-folder)


# Run project with remote JVM Debug
- mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8001"
