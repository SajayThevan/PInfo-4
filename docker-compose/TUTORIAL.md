
Build new image
---------------
In the microservice to build
```bash
mvn install -Ppackage-docker-image
docker tag unige/<SERVICE>-service pinfo4/<SERVICE>-service:develop
docker push pinfo4/<SERVICE>-service:develop
```

Stop and clean current docker containers
----------------------------------------
```bash
bash clean.sh
```

Run containers
--------------
```bash
docker-compose up
```
