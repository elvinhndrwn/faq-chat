# Menggunakan base image dari OpenJDK 17
FROM openjdk:17-jdk-alpine

# Mendefinisikan ARG untuk nama aplikasi, versi, dan pengaturan JVM
ARG APP_NAME
ARG APP_VERSION

# Menyalin file .jar yang dibangun berdasarkan nama aplikasi dan versi
COPY target/${APP_NAME}-${APP_VERSION}.jar app.jar

# Menjalankan aplikasi Spring Boot dengan pengaturan JVM
ENTRYPOINT ["java", "-jar", "/app.jar"]