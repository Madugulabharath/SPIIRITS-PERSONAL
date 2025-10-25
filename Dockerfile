# -------- Stage 1: Build WAR using Maven --------
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven descriptor and source code
COPY pom.xml .
COPY src ./src

# Build the project and create WAR file
RUN mvn clean package -DskipTests

# -------- Stage 2: Run using Tomcat --------
FROM tomcat:10.1-jdk17

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the generated WAR file from the build stage
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
