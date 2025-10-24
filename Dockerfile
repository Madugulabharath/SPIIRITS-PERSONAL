# Stage 1: Build WAR with Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the WAR
RUN mvn clean package -DskipTests

# Stage 2: Tomcat
FROM tomcat:10.1-jdk17

# Remove default ROOT webapp and copy our WAR
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
