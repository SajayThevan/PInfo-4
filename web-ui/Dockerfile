### STAGE 1: Build ###
FROM node:latest AS build
WORKDIR /app
COPY package*.json ./
RUN cd /app && npm install
COPY . /app
RUN cd /app && npm run build --prod --output-path=dist
### STAGE 2: Run ###
FROM nginx:latest
RUN rm -rf /usr/share/nginx/html/*
COPY nginx.default /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/web-ui /usr/share/nginx/html
EXPOSE 80
