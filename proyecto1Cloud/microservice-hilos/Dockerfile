FROM openjdk:17-oracle


WORKDIR /app/hilos


COPY ./microservice-hilos-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9095


CMD ["java","-jar","app.jar"]