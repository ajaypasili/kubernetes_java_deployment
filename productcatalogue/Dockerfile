FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace
COPY pom.xml .
COPY src src
RUN ./mvnw -v >/dev/null 2>&1 || true
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:21-jre-jammy
RUN useradd --system --create-home appuser
WORKDIR /app
COPY --from=build /workspace/target/productcatalogue-1.0.0.jar app.jar
USER appuser
EXPOSE 8020
ENTRYPOINT ["java","-jar","/app/app.jar"]
