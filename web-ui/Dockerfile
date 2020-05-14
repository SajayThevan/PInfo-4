# FROM nginx:alpine
#
# COPY ./dist /usr/share/nginx/html
# COPY nginx.default /etc/nginx/conf.d/default.conf
#
# EXPOSE 80

### STAGE 1: Build ###
FROM node:latest AS build
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
### STAGE 2: Run ###
FROM nginx:latest
COPY nginx.default /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/app/dist/web-ui /usr/share/nginx/html
