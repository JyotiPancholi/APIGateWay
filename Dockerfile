FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=./target/gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /usr/app/gateway.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","gateway.jar"]