FROM openjdk:17-jdk-slim


WORKDIR /app/estados


COPY microservice-estados-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9093


CMD ["java","-jar","app.jar"]