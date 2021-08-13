FROM adoptopenjdk/openjdk11:alpine
MAINTAINER Gladkov Nikolai <nikolai.gladkov@orioninc.com>
ADD target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/user-service-0.0.1-SNAPSHOT.jar"]