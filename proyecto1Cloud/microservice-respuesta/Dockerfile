FROM openjdk:17-oracle


WORKDIR /app/respuestas


COPY target/msvc-respuestas-*.jar app.jar


EXPOSE 9094


ENTRYPOINT ["java","-jar","app.jar"]