FROM openjdk:17-oracle


WORKDIR /app/estados


COPY target/msvc-estados-*.jar app.jar


EXPOSE 9093


ENTRYPOINT ["java","-jar","app.jar"]