FROM gradle:8.13.0-jdk21 AS builder
COPY --chown=gradle:gradle . /home/gradle/app
WORKDIR /home/gradle/app
RUN gradle build -x test --no-daemon
RUN ls -al build/libs
RUN rm -v build/libs/*-plain.jar


FROM openjdk:21-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /home/gradle/app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

