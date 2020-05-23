echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker tag unige/profile-service    pinfo4/profile-service:latest
docker tag unige/ingredient-service pinfo4/ingredient-service:latest
docker tag unige/recipe-service     pinfo4/recipe-service:latest
docker tag unige/challenge-service  pinfo4/challenge-service:latest
docker tag unige/web-ui             pinfo4/web-ui:latest
docker push pinfo4/profile-service:latest
docker push pinfo4/ingredient-service:latest
docker push pinfo4/recipe-service:latest
docker push pinfo4/challenge-service:latest
docker push pinfo4/web-ui:latest
docker push pinfo4/login-theme:latest
