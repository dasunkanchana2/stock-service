# Stock-Service
Stock-Service is developed to expose general stock maintenance related data
Java 11 SDK as a language and Spring Boot(2.3.7) as framework to develop this API.This API is a pre-configured 
Maven project containing all required dependencies.

## 1.0 Getting Started
### 1.1 Prerequisites

* Git
* Docker
* JDK 11 or later
* Spring Boot 2.3.7
* Maven 3.0 or later
* MySQL 8.0.32

#### <a name="1.2 Clone"></a> 1.2 Clone

To get started you can simply clone this repository using git and checkout from development branch:

```
git clone https://github.com/dasunkanchana2/stock-service.git
cd stock-service
git checkout <branch>

```

#### <a name="1.3 Configuration"></a> 1.3 Configuration

In order to get [stock-service] working you have to provide the following settings in the application.properties file
placed in the main/resource directory:

```
#
# JDBC properties
#
spring.datasource.url=jdbc:mysql://mysqldb:3306/<db-name>?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=<?>
spring.datasource.password=<?>
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=update

#
# Hibernate properties
#
spring.jpa.show-sql=true
spring.jpa.
# Ask hiberante to use the same column names given in the model
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

#
# Other Spring properties
#
server.servlet.context-path=/api
server.port=8087

#
#Swagger
#
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER

```

#### <a name="1.4 Build an executable JAR"></a> 1.4 Build an executable JAR

You can build a single executable JAR file that contains all the necessary dependencies, classes, 
and resources with:
```
mvn clean package
or
mvn clean install
```

#### <a name="1.5 Run the application"></a> 1.5 Run the application
1.5.1 Maven 
```
mvn spring-boot:run
```

1.5.2 Executable Jar File
```
java -jar /stock-service/target/*.jar
```

1.5.3 Docker

To get started you can simply clone this repository using git(stage-branch): [Refer 1.2](#1.2 Clone)

In order to get [stock-service] working you have to provide the following settings: [Refer 1.3](#1.3 Configuration)

Build a single executable JAR file that contains all the necessary dependencies, classes, and resources with: [Refer 1.4](#1.4 Build an executable JAR)

Find the Dockerfile is located in stock-service/Dockerfile

Build stock-service application docker image
```
docker build -t stock-service:01 -f Dockerfile .
```

MySQL work as a database layer for this application and pull MySQL docker image(latest) in to docker container environment

```
docker pull mysql:5.7
```

Find created docker images application docker image and MySQL docker image

```
 docker images
```

Create docker network for application to communicate with MySQL database

```
docker network create springboot-mysql-net
```

Run the MySQL container in the network and wait for few minutes

```
docker run -it --name mysqldb --network=springboot-mysql-net -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=stock -e MYSQL_USER=sys -e MYSQL_PASSWORD=1234 -d mysql:5.7
```

Run application docker image

```
docker run --network=springboot-mysql-net --name stock-service-app -p 8087:8087 -d stock-service:01
```

Find started docker container

```
docker ps
```

Get logs

```
docker logs -f <CONTAINER_ID>
```

#### 1.6 Deployment
Deployment works through docker compose and execute docker-compose.yml file placed application root directory
(N/A in this release)
```
docker-compose up -d
```
 
## 2.0 Application Testing


### 2.1 Endpoints

#### 2.1.1 Swagger documentation(API Documentation)
```
http://localhost:8087/api/swagger-ui/index.html#/stock-controller
```
