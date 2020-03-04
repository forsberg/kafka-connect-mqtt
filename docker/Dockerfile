FROM wurstmeister/kafka:2.12-2.2.0

RUN mkdir -p /opt/kafka/logs

RUN addgroup -g 1001 -S ncs && adduser -u 1001 -S ncs -G ncs

COPY build/libs/kafka-connect-mqtt-*.jar /libs/
COPY build/output/lib/*.jar /libs/
COPY docker/entrypoint.sh /

ENV CLASSPATH="$CLASSPATH:/libs/*"
USER ncs

ENTRYPOINT /entrypoint.sh
