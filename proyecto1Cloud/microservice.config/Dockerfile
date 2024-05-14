FROM openjdk:17-oracle


WORKDIR /app/config-server


COPY ./target/microservice.config-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8888


CMD ["java","-jar","app.jar"]
