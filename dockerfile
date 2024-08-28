# Verwenden des offiziellen OpenJDK-Images als Basisbild
FROM openjdk:17-jdk-slim

# Arbeitsverzeichnis im Container festlegen
WORKDIR /app

# Maven Wrapper und Quellcodedateien kopieren
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY src ./src

# Abhängigkeiten herunterladen und Anwendung bauen
RUN ./mvnw clean package -DskipTests

# Port 8080 für den Zugriff auf die Anwendung freigeben
EXPOSE 8080

# Anwendung starten
CMD ["java", "-jar", "target/hotelreservierungsapp-0.0.1-SNAPSHOT.jar"]
