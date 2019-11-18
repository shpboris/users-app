# users-app
Users REST API based on Spring Boot, Hibernate and Swagger

1. The application is accessible on http://localhost:8000/users-app/swagger-ui.html

2. The following environment variables pointing to existing MySql database should be supplied
MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD

3. The application's docker image can be found at
https://hub.docker.com/repository/docker/shpboris/usersrestserver

4. To expose the application using plain docker on port 8888, run the following: sudo docker run -d --name mysql-docker -e MYSQL_URL=jdbc:mysql://10.244.1.31:3306/users-db -e MYSQL_USER=shpboris -e MYSQL_PASSWORD=pass123 -p 8888:8000 shpboris/usersrestserver

5. To deploy both MySql DB and users REST application to Kubernetes, make sure to have node named worker01 with /mnt/data directory in it.
Then execute the following using files from src\main\resources\deployment folder of this project:
sudo kubectl apply -f secrets-and-config-maps.yml

sudo kubectl apply -f persistence-volume-deployment.yml

sudo kubectl apply -f my-sql-deployment.yml

sudo kubectl apply -f users-rest-server-deployment.yml

