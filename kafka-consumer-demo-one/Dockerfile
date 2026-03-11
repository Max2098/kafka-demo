FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY ./kafka-consumer-demo-one/gradlew ./
COPY ./kafka-consumer-demo-one/gradle ./gradle/

COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./lombok.config ./

RUN chmod +x ./gradlew && \
    ./gradlew --version

RUN echo "rootProject.name = 'kafka-demo'" > settings.gradle && \
    echo "include 'kafka-consumer-demo-one'" >> settings.gradle

COPY ./kafka-consumer-demo-one/build.gradle ./kafka-consumer-demo-one/
COPY ./kafka-consumer-demo-one/src ./kafka-consumer-demo-one/src/

RUN chmod +x ./gradlew && \
    ./gradlew :kafka-consumer-demo-one:build -x test --no-daemon -Dorg.gradle.vfs.watch=false

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/kafka-consumer-demo-one/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]