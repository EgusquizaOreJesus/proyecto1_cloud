FROM openjdk:17-oracle


WORKDIR /app/respuestas


COPY ./target/microservice-respuesta-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9094


CMD ["java","-jar","app.jar"]