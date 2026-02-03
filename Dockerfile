# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and config
COPY src ./src
COPY testng.xml .

# Build and copy the AspectJ agent to target folder
RUN mvn clean package -DskipTests
RUN mvn process-test-resources

# Stage 2: Runtime stage
FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app

# Copy the entire app including the agent in target/
COPY --from=build /app /app

ENV BASE_URL=https://fakerestapi.azurewebsites.net

# Run tests with dynamic Base URL
ENTRYPOINT ["sh", "-c", "mvn test -DbaseUrl=${BASE_URL}"]