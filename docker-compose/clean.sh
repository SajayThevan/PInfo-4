#!/bin/bash
docker ps -q | xargs docker stop
docker ps -a -q | xargs docker rm
docker images | grep "pinfo4" | awk '{print $1}' | xargs docker rmi
