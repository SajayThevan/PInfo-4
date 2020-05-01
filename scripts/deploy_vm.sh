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
  # Config Maps
  cd ~/fridgehub-sql
  wget https://raw.githubusercontent.com/PInfo-2020/PInfo-4/master/sql/ingredient.sql
  wget https://raw.githubusercontent.com/PInfo-2020/PInfo-4/master/sql/profile.sql
  wget https://raw.githubusercontent.com/PInfo-2020/PInfo-4/master/sql/recipe.sql
  wget https://raw.githubusercontent.com/PInfo-2020/PInfo-4/master/sql/challenge.sql
  ls
  microk8s kubectl delete configmap ingredient-scripts
  microk8s kubectl create configmap ingredient-scripts  --from-file ./ingredient.sql
  microk8s kubectl delete configmap profile-scripts
  microk8s kubectl create configmap profile-scripts  --from-file ./profile.sql
  microk8s kubectl delete configmap recipe-scripts
  microk8s kubectl create configmap recipe-scripts  --from-file ./recipe.sql
  microk8s kubectl delete configmap challenge-scripts
  microk8s kubectl create configmap challenge-scripts  --from-file ./challenge.sql
  helm install $RELEASE hung-repo/fridgehub
fi
