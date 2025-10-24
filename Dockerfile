# Stage 1: Build WAR with Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml . 
COPY src ./src
COPY WebContent ./WebContent   # match your webapp folder

# Build the WAR
RUN mvn clean package

# Stage 2: Tomcat
FROM tomcat:10.1-jdk17

# Copy the ROOT.war to Tomcat
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war



EXPOSE 8080

CMD ["catalina.sh", "run"]
