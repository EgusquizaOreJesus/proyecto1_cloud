FROM openjdk:17-oracle


WORKDIR /app/hilos


COPY target/msvc-hilos-*.jar app.jar


EXPOSE 9095


ENTRYPOINT ["java","-jar","app.jar"]