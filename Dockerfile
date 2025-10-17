# Stage 1: Build WAR with Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build

# Copy pom.xml and source code
COPY pom.xml /app/
COPY src /app/src
COPY WebContent /app/WebContent

WORKDIR /app

# Build the WAR file
RUN mvn clean package

# Stage 2: Run WAR with Tomcat
FROM tomcat:10.1-jdk17

# Copy WAR from build stage
COPY --from=build /app/target/SpiritsNew.war /usr/local/tomcat/webapps/SpiritsNew.war

EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
