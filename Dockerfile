FROM openjdk:8-jdk-alpine
MAINTAINER org.wjanaszek
VOLUME /tmp
EXPOSE 8080
ADD build/libs/results-api-0.0.1-SNAPSHOT.jar results-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","results-api.jar"]
