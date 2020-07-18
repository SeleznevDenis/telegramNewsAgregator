FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 9080
ENTRYPOINT ["java","-jar","/app.jar"]