scp -r login-page smithlu7@129.194.69.131:~/login-page-deploy
scp Dockerfile smithlu7@129.194.69.131:~/login-page-deploy
ssh smithlu7@129.194.69.131 'docker build login-page-deploy --tag pinfo4/login-theme:latest'
ssh smithlu7@129.194.69.131 'cat ~/login-page-deploy/password.txt | docker login -u pinfo4 --password-stdin'
ssh smithlu7@129.194.69.131 'docker push pinfo4/login-theme:latest'
ssh smithlu7@129.194.69.131 'bash manual-deploy/clean.sh'
sleep 5
ssh smithlu7@129.194.69.131 'bash manual-deploy/deploy_vm.sh'
