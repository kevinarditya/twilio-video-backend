FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal

WORKDIR /home/apps
COPY build/libs/twilio-video-0.0.1-SNAPSHOT.jar application.jar

CMD java -Xss512k -Xms64m -Xmx256m -jar application.jar
