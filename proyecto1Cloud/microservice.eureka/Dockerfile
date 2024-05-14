FROM openjdk:17-oracle


WORKDIR /app/eureka-server


COPY target/eureka-server-*.jar app.jar


EXPOSE 8761


ENTRYPOINT ["java","-jar","app.jar"]