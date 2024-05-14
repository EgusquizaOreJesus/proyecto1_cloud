FROM openjdk:17-oracle


WORKDIR /app


COPY ./target/config-server-0.0.1-SNAPSHOT.jar /app/config-server-0.0.1-SNAPSHOT.jar


EXPOSE 8888


CMD  ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]