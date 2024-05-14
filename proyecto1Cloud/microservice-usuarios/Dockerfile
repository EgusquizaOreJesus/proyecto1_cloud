FROM openjdk:17-jdk-slim


WORKDIR /app/usuarios


COPY microservice-usuarios-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9092


CMD ["java","-jar","app.jar"]