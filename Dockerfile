FROM openjdk:8-jdk-alpine as bouncer-builder
ARG WORKDIR=/usr/local/src/bouncer
RUN mkdir -p ${WORKDIR}
COPY . ${WORKDIR}
WORKDIR ${WORKDIR}
RUN ./gradlew build -x test

FROM openjdk:8-jdk-alpine
RUN apk --update add iputils curl #postgresql-client redis
RUN addgroup -S bouncer && adduser -S bouncer -G bouncer
USER bouncer:bouncer
ARG WORKDIR=/home/bouncer
WORKDIR ${WORKDIR}
COPY --from=bouncer-builder /usr/local/src/bouncer/build/libs/*.jar ${WORKDIR}/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
# ENTRYPOINT tail -f /dev/null