FROM openjdk:17-jdk-slim


WORKDIR /app/respuestas


COPY microservice-respuesta-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9094


CMD ["java","-jar","app.jar"]