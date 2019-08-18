#!/bin/sh

dockerTag=latest

#maven
mvn clean package

#docker
modules=(sc-eureka-server microservice-repertory-server microservice-product-server)
for module in ${modules[*]}
do
    docker build -t "spring-cloud-solution-example/${module}:${dockerTag}" ${module}
done

#docker-compose
docker-compose up -d



