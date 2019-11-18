FROM java:8-jre
RUN mkdir -p /opt/usersapp/ 
COPY ./users-app-0.0.1-SNAPSHOT.jar /opt/usersapp/
WORKDIR /opt/usersapp/
EXPOSE 8000
CMD ["java", "-jar", "users-app-0.0.1-SNAPSHOT.jar"]
