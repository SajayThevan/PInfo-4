docker build . --tag pinfo4/login-theme:latest
docker login -u pinfo4
docker push pinfo4/login-theme:latest
ssh smithlu7@129.194.69.131 'bash manual-deploy/clean.sh'
sleep 5
ssh smithlu7@129.194.69.131 'bash manual-deploy/deploy_vm.sh'
