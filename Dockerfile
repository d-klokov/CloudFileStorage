FROM maven:3.8.3-openjdk-17 as build
COPY src /home/cloudfilestorage/src
COPY pom.xml /home/cloudfilestorage
RUN mvn -f /home/cloudfilestorage/pom.xml clean package -DskipTests
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/cloudfilestorage/target/CloudFileStorage.jar"]