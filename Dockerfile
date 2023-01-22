FROM eclipse-temurin:11-alpine
EXPOSE 8087:8087
ADD /target/stock-service-0.0.1-SNAPSHOT.jar stock-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","stock-service-0.0.1-SNAPSHOT.jar"]