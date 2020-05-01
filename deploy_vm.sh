#!/bin/bash

# This script is ran on the Virtual Machine at: smithlu7@129.194.69.131
# It deploys the application 'Kernel' using 'helm install' or 'helm upgrade'

RELEASE=fridge-hub
helm repo add hung-repo https://pinfo-2020.github.io/PInfo-4/
helm repo update

if helm list --all-namespaces -a | grep -q $RELEASE
then
  echo "$RELEASE exists"
  helm upgrade $RELEASE hung-repo/fridgehub
else
  echo "$RELEASE doesn't exist"
  helm install $RELEASE hung-repo/fridgehub
fi
