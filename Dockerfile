FROM adoptopenjdk/openjdk12:alpine-jre
COPY target/MediScreenMongo-0.0.1-SNAPSHOT.jar notes.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "notes.jar"]