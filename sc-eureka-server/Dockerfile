FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/sc-eureka-server-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar", "--spring.profiles.active=docker"]
EXPOSE 8761