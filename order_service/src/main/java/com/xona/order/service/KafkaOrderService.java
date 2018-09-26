package com.xona.order.service;

import com.xona.order.kafka.KeySet;

public interface KafkaOrderService {

    void send(String topic, KeySet key, String payload);

}
