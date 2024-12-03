FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY pom.xml ./pom.xml
COPY mvnw ./mvnw
COPY .mvn ./.mvn

RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
