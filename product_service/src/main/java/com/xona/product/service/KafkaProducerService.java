package com.xona.product.service;

import com.xona.product.kafka.KeySet;

public interface KafkaProducerService {

    void send(String topic, KeySet key, String payload);

}
