# ===== Стадия сборки =====
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /usofrt

COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

# ===== Стадия выполнения =====

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/usofrt-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
