FROM eclipse-temurin:17-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV PORT 8080
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
