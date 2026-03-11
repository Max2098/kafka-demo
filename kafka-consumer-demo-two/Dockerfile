FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY ./kafka-consumer-demo-two/gradlew ./
COPY ./kafka-consumer-demo-two/gradle ./gradle/

COPY ./settings.gradle ./
COPY ./build.gradle ./
COPY ./lombok.config ./

RUN chmod +x ./gradlew && \
    ./gradlew --version

RUN echo "rootProject.name = 'kafka-demo'" > settings.gradle && \
    echo "include 'kafka-consumer-demo-two'" >> settings.gradle

COPY ./kafka-consumer-demo-two/build.gradle ./kafka-consumer-demo-two/
COPY ./kafka-consumer-demo-two/src ./kafka-consumer-demo-two/src/

RUN chmod +x ./gradlew && \
    ./gradlew :kafka-consumer-demo-two:build -x test --no-daemon -Dorg.gradle.vfs.watch=false

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/kafka-consumer-demo-two/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]