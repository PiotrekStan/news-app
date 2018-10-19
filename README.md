# News-API Feed

[![Build Status](https://travis-ci.org/PiotrekStan/news-app.svg?branch=master)](https://travis-ci.org/PiotrekStan/news-app)

Simple news app for laoding the news provided by [NewsAPI.org](https://newsapi.org).
Application is based on two modules:
1. news-server - Spring Boot backend which serves also Angular 6 front end.
2. news-web - Frontend based on Angular 6 framework

### Installation
##### Prerequisites
- JDK
- [Maven](https://maven.apache.org/)
- [Docker with docker-compose](https://www.docker.com/)
- generated API key for [NewsAPI.org](https://newsapi.org)

##### Steps for installation
Before installation you have to set **api.key** property in **application.properties** :

```sh
$ cd ./news-app/news-server/src/main/resources/
$ vim  application.properties

set value for:
    news.api.key = $API_KEY
```

After setting the api.key you have to build project and Docker image. 

```sh
$ cd ./news-app
$ mvn clean install
```

Next run the docker-compose with **docker-compose.yml* file.

```sh
$ docker-compose up -d
```

By default application is running on:
```
http://localhost:8080
```
Swagger UI for REST API documentation is available on:
```
http://localhost:8080/swagger-ui.html
```

### Tech stack:
Project on github has configured Travis-CI for automatically maven builds.
##### Backend:
- Spring Boot
- Mockito
- Docker
- Maven
- [front-end-maven-plugin](https://github.com/eirslett/frontend-maven-plugin)
- [dockerfile-maven-plugin](https://github.com/spotify/dockerfile-maven)
- [Swagger2](https://swagger.io/)

##### Frontend:
- Angular 6
- [Ng Bootstrap](https://ng-bootstrap.github.io)
- [Bootstrap](https://getbootstrap.com/)

### Contact:
If you have any questions contact me via stankiewicz.piotr.w@gmail.com

