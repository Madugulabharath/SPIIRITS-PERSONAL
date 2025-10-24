# Simple Dockerfile for prebuilt WAR

FROM tomcat:10.1-jdk17

# Remove default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your prebuilt WAR
COPY target/SpiritsNew.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
