ssh smithlu7@129.194.69.131 'rm -rf ~/login-page-deploy/login-page'
scp -r login-page smithlu7@129.194.69.131:~/login-page-deploy
scp Dockerfile smithlu7@129.194.69.131:~/login-page-deploy
ssh smithlu7@129.194.69.131 'bash ~/login-page-deploy/login-page.sh'
