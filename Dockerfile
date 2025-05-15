FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY target/dz-service-auth-0.0.1-SNAPSHOT.jar my-app.jar

CMD ["java", "-jar", "my-app.jar"]