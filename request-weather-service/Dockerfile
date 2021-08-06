FROM adoptopenjdk/openjdk11:alpine
MAINTAINER Gladkov Nikolai <nikolai.gladkov@orioninc.com>
ADD target/request-weather-service-2.5.2.jar request-weather-service-2.5.2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/request-weather-service-2.5.2.jar"]
