package com.xona.user.service;

import com.xona.user.kafka.KeySet;

public interface KafkaProducerService {

    void send(String topic, KeySet key, String payload);

}
