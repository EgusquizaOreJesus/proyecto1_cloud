FROM openjdk:17-jdk-alpine


WORKDIR /app/eureka-server


COPY microservice.eureka-0.0.1.jar app.jar


EXPOSE 8761


CMD ["java","-jar","app.jar"]