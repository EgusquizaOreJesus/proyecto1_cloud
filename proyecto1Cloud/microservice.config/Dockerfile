FROM openjdk:17-jdk-slim


WORKDIR /app/config-server


COPY microservice.config-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8081


CMD  ["java","-jar","app.jar"]
