
FROM eclipse-temurin:17-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV PORT 8080
ENV JAVA_OPTS ""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
