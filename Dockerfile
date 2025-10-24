# -------------------------------
# Stage 1: Build WAR with Maven
# -------------------------------
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy necessary files for Maven build
COPY pom.xml .
COPY src ./src
COPY WebContent ./WebContent

# Build the WAR file
RUN mvn clean package

# -------------------------------
# Stage 2: Deploy WAR in Tomcat
# -------------------------------
FROM tomcat:10.1-jdk17

# Copy WAR file from the build stage into Tomcat webapps folder
COPY --from=build /app/target/SpiritsNew.war /usr/local/tomcat/webapps/SpiritsNew.war

# Expose Tomcat default port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
