## Introduction

reference dockerized cloud microservices with spring boot rest, cassandra, kafka &amp; zookeeper

`Pet` microservices project.

* Docker
* Spring Boot
* Cassandra
* ZooKeeper
* Kafka
* REST API

## Developer Environment

Build docker images
```
chmod +x ./docker-build.sh && ./docker-build.sh
```
Run
```
docker-compose up
```
Delete every Docker containers
Must be run first because images are attached to containers
```
docker rm -f $(docker ps -a -q)
```
Delete every Docker image
```
docker rmi -f $(docker images -q)
```
Inspecting docker IP addresses
```
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' api-service
```

### Architecture

![](readme/diagram.png)

