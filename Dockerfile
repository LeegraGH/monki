FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY /build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/monki
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=2912

ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_DATABASE=postgresql
ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

EXPOSE 8080