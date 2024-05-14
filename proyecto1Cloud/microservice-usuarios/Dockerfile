FROM openjdk:17-oracle


WORKDIR /app/usuarios


COPY ./target/microservice-usuarios-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9092


CMD ["java","-jar","app.jar"]