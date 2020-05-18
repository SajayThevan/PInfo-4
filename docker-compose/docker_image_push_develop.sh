echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker tag unige/profile-service    pinfo4/profile-service:develop
docker tag unige/ingredient-service pinfo4/ingredient-service:develop
docker tag unige/recipe-service     pinfo4/recipe-service:develop
docker tag unige/challenge-service  pinfo4/challenge-service:develop
docker tag unige/web-ui             pinfo4/web-ui:develop
docker push pinfo4/profile-service:develop
docker push pinfo4/ingredient-service:develop
docker push pinfo4/recipe-service:develop
docker push pinfo4/challenge-service:develop
docker push pinfo4/web-ui:develop
