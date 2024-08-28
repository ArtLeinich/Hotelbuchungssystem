# Используем официальный образ OpenJDK как базовый образ
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем Maven Wrapper и исходные файлы
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY src ./src

# Скачиваем зависимости и собираем приложение
RUN ./mvnw clean package -DskipTests

# Экспонируем порт 8080 для доступа к приложению
EXPOSE 8080

# Запуск приложения
CMD ["java", "-jar", "target/hotelreservierungsapp-0.0.1-SNAPSHOT.jar"]
