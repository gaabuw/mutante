# ========================================
# ETAPA 1: BUILD (Compilación)
# ========================================
# Usamos una imagen oficial de Java 21 (Alpine es la versión ligera)
FROM eclipse-temurin:21-jdk-alpine as build

# Creamos directorio de trabajo
WORKDIR /app

# Copiamos todo el código
COPY . .

# Damos permisos al gradlew
RUN chmod +x ./gradlew

# Compilamos (saltando tests para velocidad en deploy)
RUN ./gradlew bootJar --no-daemon -x test

# ========================================
# ETAPA 2: RUNTIME (Ejecución)
# ========================================
# Usamos la imagen JRE (Java Runtime Environment) 21, que es más ligera para correr
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo en la imagen final
WORKDIR /app

# Exponemos el puerto
EXPOSE 8080

# Copiamos el JAR. 
# TRUCO: Usamos *.jar para que no importe si tu versión es 0.0.1 o 1.0
COPY --from=build /app/build/libs/*.jar app.jar

# Ejecutamos
ENTRYPOINT ["java", "-jar", "app.jar"]
