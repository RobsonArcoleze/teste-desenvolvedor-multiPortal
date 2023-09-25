FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/testeDesenvolvedor.jar /app/app.jar
EXPOSE 8025
CMD ["java", "-jar", "app.jar"]