FROM openjdk:15-jdk-alpine
ADD target/restAPI-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar restAPI-0.0.1-SNAPSHOT.jar