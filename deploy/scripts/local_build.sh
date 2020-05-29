#!/bin/bash

TOBUILD=$1
DEVELOP=$2
TAG=latest
if [[ "$DEVELOP" == "true" ]]; then
  TAG=develop
fi
SCRIPT_DIR=$(dirname $0)
HOME=./$SCRIPT_DIR/../..

if [[ "$TOBUILD" == *"p"* ]]; then
  cd $HOME/profile-service
  mvn clean install -Ppackage-docker-image -DskipTests=true -Dmaven.test.skip=true
  docker tag unige/profile-service pinfo4/profile-service:$TAG
  docker push pinfo4/profile-service:$TAG
fi
if [[ "$TOBUILD" == *"r"* ]]; then
  cd $HOME/recipe-service
  mvn clean install -Ppackage-docker-image -DskipTests=true -Dmaven.test.skip=true
  docker tag unige/recipe-service pinfo4/recipe-service:$TAG
  docker push pinfo4/recipe-service:$TAG
fi
if [[ "$TOBUILD" == *"i"* ]]; then
  cd $HOME/ingredient-service
  mvn clean install -Ppackage-docker-image -DskipTests=true -Dmaven.test.skip=true
  docker tag unige/ingredient-service pinfo4/ingredient-service:$TAG
  docker push pinfo4/ingredient-service:$TAG
fi
if [[ "$TOBUILD" == *"c"* ]]; then
  cd $HOME/challenge-service
  mvn clean install -Ppackage-docker-image -DskipTests=true -Dmaven.test.skip=true
  docker tag unige/challenge-service pinfo4/challenge-service:$TAG
  docker push pinfo4/challenge-service:$TAG
fi

ssh smithlu7@129.194.69.131 'bash ~/manual-deploy/clean.sh'
ssh smithlu7@129.194.69.131 'bash ~/manual-deploy/deploy_vm_develop.sh'
