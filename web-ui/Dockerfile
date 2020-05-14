# FROM nginx:alpine
#
# COPY ./dist /usr/share/nginx/html
# COPY nginx.default /etc/nginx/conf.d/default.conf
#
# EXPOSE 80

### STAGE 1: Build ###
FROM node:latest AS build
WORKDIR /app
COPY package*.json ./app/
RUN npm install
COPY . /app/
RUN npm run build
### STAGE 2: Run ###
FROM nginx:alpine
COPY --from=build /app/dist/web-ui /usr/share/nginx/html
