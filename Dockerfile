FROM openjdk:17-jdk

LABEL maintainer="ayushkoli772@gmail.com"

EXPOSE 8080

WORKDIR /app

COPY ./target/converto-backend-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "converto-backend-0.0.1-SNAPSHOT.jar"]