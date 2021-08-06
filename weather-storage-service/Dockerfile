FROM openjdk:11-jdk-oraclelinux8
MAINTAINER Gladkov Nikolai <nikolai.gladkov@orioninc.com>
ADD target/weather-storage-service-2.5.2.jar weather-storage-service-2.5.2.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/weather-storage-service-2.5.2.jar"]