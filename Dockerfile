FROM openjdk:17-jdk-alpine

LABEL maintainer="ayushkoli772@gmail.com"

EXPOSE 8080

ENTRYPOINT ["top", "-b"]