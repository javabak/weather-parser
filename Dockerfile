FROM openjdk:17
COPY target/weather-parser-0.0.1-SNAPSHOT.jar weather.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "weather.jar"]