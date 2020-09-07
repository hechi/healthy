# Stage 1 - the build process
FROM node:slim as build-deps
WORKDIR /usr/src/app
COPY package.json yarn.lock ./
RUN yarn
COPY . ./
RUN yarn build

# Stage 2 - the production environment
FROM nginx:1.19.1-alpine
ENV REACT_APP_HEALTHY_API_URL=http://healthy-api:8080
COPY --from=build-deps /usr/src/app/build /usr/share/nginx/html
EXPOSE 80
# Copy .env file and shell script to container
WORKDIR /usr/share/nginx/html
COPY ./env.sh .
COPY .env .

# Add bash
RUN apk add --no-cache bash

# Make our shell script executable
RUN chmod +x env.sh

# Start Nginx server
CMD ["/bin/bash", "-c", "/usr/share/nginx/html/env.sh && nginx -g \"daemon off;\""]