FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY ./kafka-producer-demo/gradlew ./
COPY ./kafka-producer-demo/gradle ./gradle/

COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./lombok.config ./

RUN chmod +x ./gradlew && \
    ./gradlew --version

RUN echo "rootProject.name = 'kafka-demo'" > settings.gradle && \
    echo "include 'kafka-producer-demo'" >> settings.gradle

COPY ./kafka-producer-demo/build.gradle ./kafka-producer-demo/
COPY ./kafka-producer-demo/src ./kafka-producer-demo/src/

RUN chmod +x ./gradlew && \
    ./gradlew :kafka-producer-demo:build -x test --no-daemon -Dorg.gradle.vfs.watch=false

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/kafka-producer-demo/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]