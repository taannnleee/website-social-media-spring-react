FROM openjdk:17
ARG FILE_JAR=target/social-media.jar

ADD ${FILE_JAR} api-service.jar

ENTRYPOINT ["java","-jar","api-service.jar"]\

EXPOSE 8080
