package com.evokly.kafka.connect.mqtt.sample;

import com.evokly.kafka.connect.mqtt.MqttMessageProcessor;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Copyright 2016 Evokly S.A.
 *
 * <p>See LICENSE file for License
 **/
public class NullKeyProcessor implements MqttMessageProcessor {
    private static final Logger log = LoggerFactory.getLogger(NullKeyProcessor.class);
    private MqttMessage mMessage;

    @Override
    public MqttMessageProcessor process(String topic, MqttMessage message) {
        log.debug("processing data for topic: {}; with message {}", topic, message);
        this.mMessage = message;
        return this;
    }

    @Override
    public SourceRecord[] getRecords(String kafkaTopic) {
        return new SourceRecord[]{new SourceRecord(null, null, kafkaTopic, null,
                Schema.OPTIONAL_STRING_SCHEMA, null,
                Schema.BYTES_SCHEMA, mMessage.getPayload())};
    }
}
