#!/bin/bash

# This script is ran on the Virtual Machine at: smithlu7@129.194.69.131
# It deploys the application 'Kernel'

cd ~/Kernel
env
# docker login index.docker.io # Credentials setup with pass on the VM
                               # Note: not needed for now as dockerHub repo is public
                               # If needed later, need to fix docker-credentials-pass (pass logs out after 2h or so and so docker login can no longer access the credentials)

# Clean up docker
docker stop $(docker ps -aq)  # Stop all containers
docker system prune -f -a # remove all stopped containers, dangling and unused(-a) images (& unused networks) , -f to avoid questions
                          # TODO: '-a' might be too much, not sure we need to delete all unused images
                          # TODO: For when were deploying the actual containers we probably wont want to stop the database containers???

docker pull pinfo4/api-gateway:latest
docker pull pinfo4/regulatory-service:latest
docker pull pinfo4/valuation-service:latest
docker pull pinfo4/instrument-service:latest
docker pull pinfo4/counterparty-service:latest
docker images
cd  docker-compose
docker-compose -f docker-compose-microservices.yml up # &
docker-compose -f docker-compose-log.yml up &
docker-compose -f docker-compose-api-gw.yml up &
echo "Finished composing"
