# Use Tomcat 10 with JDK 17
FROM tomcat:10.1-jdk17

# Copy your WAR file from target folder (Maven output)
COPY target/SpiritsNew.war /usr/local/tomcat/webapps/SpiritsNew.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat when the container runs
CMD ["catalina.sh", "run"]
