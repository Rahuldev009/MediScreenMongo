FROM openjdk:12-alpine
COPY target/MediScreenMongo-0.0.1-SNAPSHOT.jar notes.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "notes.jar"]