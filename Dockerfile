FROM openjdk:11-jre-slim

VOLUME /tmp
WORKDIR .
EXPOSE 8080

ADD build/libs/Kademlia-1.0.jar app.jar
ENV JAVA_OPTS=""
CMD java -jar app.jar