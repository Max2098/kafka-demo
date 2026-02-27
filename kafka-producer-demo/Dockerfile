FROM gradle:8-jdk21 AS build

WORKDIR /app

COPY ./kafka-producer-demo/gradlew ./
COPY ./kafka-producer-demo/gradle ./gradle/

RUN echo "rootProject.name = 'kafka-demo'\ninclude 'kafka-producer-demo'" > settings.gradle

COPY ./kafka-producer-demo/build.gradle ./kafka-producer-demo/
COPY ./kafka-producer-demo/src ./kafka-producer-demo/src/

RUN --mount=type=cache,id=gradle-cache-kafka-producer-demo,target=/home/gradle/.gradle \
    chmod +x ./gradlew && \
    ./gradlew :kafka-producer-demo:build -x test --no-daemon -Dorg.gradle.vfs.watch=false

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/kafka-producer-demo/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]