#!/bin/sh

#merge the files as we can only send in 1 property file
(cat /etc/kafka-connector/connector.properties;
    echo;
    cat /etc/kafka-config/kafka-client.properties;
    echo;
    sed 's/^/producer./' /etc/kafka-config/kafka-client.properties
) > ~/standalone-properties.properties

#merge in mqtt remote password to mqtt properties
mqtt_password=$(cat /etc/kafka-connector/remote_password)
(cat /etc/kafka-connector/mqtt.properties;
    echo;
    echo "mqtt.password=$mqtt_password";
) > ~/mqtt.properties

$KAFKA_HOME/bin/connect-standalone.sh ~/standalone-properties.properties ~/mqtt.properties -Dlog4j.configuration=file:/etc/log/log4j.properties