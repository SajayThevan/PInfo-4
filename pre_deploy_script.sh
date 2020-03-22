#!/bin/bash

# Prepares the VM for deployment Virtual Machine at: smithlu7@129.194.69.131
# It deploys the application 'Kernel'

ssh smithlu7@129.194.69.131 'rm -rf ~/Kernel/docker-compose' # Remove the current docker compose
scp -r docker-compose smithlu7@129.194.69.131:~/Kernel/
