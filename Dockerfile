FROM eclipse-temurin:21-jdk-alpine
WORKDIR /workplace

COPY target/stock-exchange-1.0.0.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "app.jar"]
