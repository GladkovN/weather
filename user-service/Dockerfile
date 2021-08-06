FROM openjdk:11-jdk-oraclelinux8
MAINTAINER Gladkov Nikolai <nikolai.gladkov@orioninc.com>
ADD target/user-service.jar user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/user-service.jar"]