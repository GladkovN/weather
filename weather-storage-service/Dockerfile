FROM adoptopenjdk/openjdk11:alpine
MAINTAINER Gladkov Nikolai <nikolai.gladkov@orioninc.com>
ADD target/weather-storage-service-2.5.2.jar weather-storage-service-2.5.2.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/weather-storage-service-2.5.2.jar"]