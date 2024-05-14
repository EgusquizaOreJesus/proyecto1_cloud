FROM openjdk:17-oracle


WORKDIR /app/usuarios


COPY target/msvc-usuarios-*.jar app.jar


EXPOSE 9092


ENTRYPOINT ["java","-jar","app.jar"]