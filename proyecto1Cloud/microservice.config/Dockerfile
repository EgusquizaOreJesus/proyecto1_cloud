FROM openjdk:17-oracle


WORKDIR /app/config-server


COPY target/config-server-*.jar app.jar


EXPOSE 8888


ENTRYPOINT ["java","-jar","app.jar"]