# This script is ran on the Virtual Machine at: smithlu7@129.194.69.131
# It deploys the application 'Kernel'

#!/bin/bash
cd ~/Kernel
docker login index.docker.io # Credentials setup with pass on the VM
docker pull pinfo4/api-gateway:latest
docker pull pinfo4/regulatory-service:latest
docker pull pinfo4/valuation-service:latest
docker pull pinfo4/instrument-service:latest
docker pull pinfo4/counterparty-service:latest
docker images
cd  docker-compose
docker-compose -f docker-compose-microservices.yml up &
docker-compose -f docker-compose-log.yml up &
docker-compose -f docker-compose-api-gw.yml up &
