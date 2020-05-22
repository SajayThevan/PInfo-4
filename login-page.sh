scp -r ./deploy/keycloak/login-page smithlu7@129.194.69.131:/home/smithlu7/login-page
ssh smithlu7@129.194.69.131 'microk8s kubectl cp login-page fridge-hub-keycloak-0:/opt/jboss/keycloak/themes'
# ssh smithlu7@129.194.69.131 'microk8s kubectl cp login-pages/standalone.xml fridge-hub-keycloak-0:/opt/jboss/keycloak/standalone/configuration'
