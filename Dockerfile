# Stage 1: Build WAR with Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and sources
COPY pom.xml . 
COPY src ./src
COPY WebContent ./WebContent

# Build WAR
RUN mvn clean package

# Stage 2: Tomcat
FROM tomcat:10.1-jdk17

# Copy ROOT.war
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
