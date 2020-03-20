FROM node:latest AS build-phase

WORKDIR /webapp
COPY ./ /webapp/

RUN npm install
RUN npm update

RUN npm run-script build:prod --output-path=./dist

FROM nginx

COPY --from=build-phase /webapp/dist /usr/share/nginx/html
COPY nginx.default /etc/nginx/conf.d/default.conf
COPY htpasswd /etc/nginx/htpasswd
