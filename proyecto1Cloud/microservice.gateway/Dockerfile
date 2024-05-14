FROM openjdk:17-oracle


WORKDIR /app/gateway


COPY target/gateway-*.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java","-jar","app.jar"]