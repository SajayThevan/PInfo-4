#!/bin/bash

# This script is ran on the Virtual Machine at: smithlu7@129.194.69.131
# It deploys the application 'Kernel'

cd ~/Kernel
# docker login index.docker.io # Credentials setup with pass on the VM
                               # Note: not needed for now as dockerHub repo is public
                               # If needed later, need to fix docker-credentials-pass (pass logs out after 2h or so and so docker login can no longer access the credentials)
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
