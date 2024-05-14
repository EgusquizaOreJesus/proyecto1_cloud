FROM openjdk:17-oracle


WORKDIR /app/eureka-server


COPY ./target/microservice.eureka-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8761


CMD ["java","-jar","app.jar"]