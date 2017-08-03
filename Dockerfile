FROM java:8-jdk-alpine

COPY build/distributions/publisher-service.zip /usr/lib/adscoop/publisher-service.zip

RUN apk update && apk upgrade
RUN apk add bash

RUN cd /usr/lib/adscoop/ && unzip publisher-service.zip
RUN rm /usr/lib/adscoop/publisher-service.zip

EXPOSE 8184
