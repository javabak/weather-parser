FROM maven:3.8.5-openjdk-17 AS build
COPY /src /src
COPY /pom.xml /
RUN mvn -f /pom.xml clean package


FROM openjdk:17
COPY --from=build /target/weather-parser-0.0.1-SNAPSHOT.jar weather.jar
EXPOSE 8080
CMD ["java", "-jar", "weather.jar"]