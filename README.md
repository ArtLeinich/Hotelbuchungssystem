# Hotelreservierungssystem

Dieses Projekt ist ein **Hotelreservierungssystem**, das moderne Technologien integriert, um eine effiziente und benutzerfreundliche Lösung für Hotelbuchungen zu bieten.

## Verwendete Technologien

### 1. Spring Boot
- **Beschreibung**: Ein Framework, das eine schnelle und einfache Entwicklung von Java-Anwendungen ermöglicht.
- **Funktion**: Sorgt für die grundlegende Struktur und Konfiguration der Anwendung und ermöglicht eine schnelle Implementierung der Kernfunktionalitäten.

### 2. JPA (Java Persistence API)
- **Beschreibung**: API zur Verwaltung der Persistenzschicht.
- **Funktion**: Ermöglicht die nahtlose Speicherung und Abfrage von Daten in der Datenbank, indem Objekte in relationale Datenbanktabellen abgebildet werden.

### 3. H2
- **Beschreibung**: Eine eingebettete relationale Datenbank, die für Entwicklungs- und Testzwecke verwendet wird.
- **Funktion**: Leichtgewichtig und ermöglicht eine einfache und schnelle Datenbankverwaltung innerhalb des Projekts.

### 4. Thymeleaf
- **Beschreibung**: Eine Template-Engine zur Generierung von HTML-Seiten.
- **Funktion**: Integriert sich nahtlos mit Spring Boot und bietet eine leistungsfähige Möglichkeit, dynamische Webinhalte zu erstellen.

### 5. Bootstrap
- **Beschreibung**: Ein CSS-Framework für Design und Benutzeroberfläche.
- **Funktion**: Ermöglicht die schnelle Erstellung von responsiven und modernen Webanwendungen durch vorgefertigte Designkomponenten und Layouts.

## Docker-Konfiguration

### Dockerfile

Das Projekt enthält eine `Dockerfile`, die die Anwendung in einem Docker-Container verpackt. Die wichtigsten Schritte in der `Dockerfile` sind:

- **Basisbild**: Verwendet wird ein offizielles `openjdk:17-jdk-slim`-Bild als Basis.
- **Arbeitsverzeichnis**: Das Arbeitsverzeichnis im Container wird auf `/app` gesetzt.
- **Dateien kopieren**: Kopiert den Maven Wrapper und die Quellcodedateien in den Container.
- **Abhängigkeiten herunterladen und Anwendung bauen**: Führt den Befehl `./mvnw clean package -DskipTests` aus, um die Abhängigkeiten herunterzuladen und die Anwendung zu bauen, wobei Tests übersprungen werden.
- **Port exponieren**: Port 8080 wird exponiert, damit die Anwendung außerhalb des Containers zugänglich ist.
- **Startbefehl**: Die Anwendung wird mit dem Befehl `java -jar target/hotelreservierungsapp-0.0.1-SNAPSHOT.jar` gestartet.

### Docker Compose

Mit `docker-compose.yml` können Sie die Anwendung und ihre abhängigen Dienste einfach hochfahren. Die Datei definiert zwei Dienste:

- **PostgreSQL-Datenbank (postgres)**:
  - Verwendet das Bild `postgres:15`.
  - Setzt die Umgebungsvariablen für den Datenbankbenutzer, das Passwort und den Datenbanknamen.
  - Mappt den Containerport 5432 auf den Hostport 5432.
  - Nutzt ein benanntes Volume `postgres_data` für persistente Datenspeicherung.

- **Spring Boot Anwendung (springboot-app)**:
  - Baut das Docker-Image der Anwendung basierend auf der `Dockerfile`.
  - Setzt Umgebungsvariablen für die Verbindung zur PostgreSQL-Datenbank.
  - Mappt den Containerport 8080 auf den Hostport 8080.
  - Abhängig von der PostgreSQL-Datenbank, sodass diese zuerst gestartet wird.

### Dienste starten

Um die Anwendung zu starten, verwenden Sie den folgenden Befehl:

```sh
docker-compose up
