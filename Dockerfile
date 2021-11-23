FROM openjdk:17-jdk-alpine
ENV WORKDIR=/app
WORKDIR /app
ADD . $WORKDIR
ENV PATH=$PATH:$WORKDIR/script
RUN apk add curl bash direnv