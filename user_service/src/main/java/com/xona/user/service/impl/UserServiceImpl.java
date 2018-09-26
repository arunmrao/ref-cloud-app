package com.xona.user.service.impl;

import com.xona.user.entity.User;
import com.xona.user.mapper.JsonMapperWrapper;
import com.xona.user.repository.UserRepository;
import com.xona.user.service.KafkaProducerService;
import com.xona.user.service.UserService;
import com.xona.user.kafka.KeySet;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final KafkaProducerService kafkaProducerService;
    private final JsonMapperWrapper jsonMapper;

    private final String topic;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, KafkaProducerService kafkaProducerService,
                           JsonMapperWrapper jsonMapper,
                           @Value("${spring.kafka.consumer.topic.user}") String topic) {
        this.userRepository = userRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.jsonMapper = jsonMapper;
        this.topic = topic;
    }

    @Override
    public User save(User user) {
        LOGGER.info("Save user: {}", user);
        User saved = userRepository.save(user);
        kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(saved));
        return saved;
    }

    @Override
    public User update(UUID id, User updated) {
        LOGGER.info("Update user, id: {}, {}", id, updated);
        User product = userRepository.findOne(id);
        product.setFirstName(updated.getFirstName());
        product.setLastName(updated.getLastName());
        product.setEmail(updated.getEmail());
        User saved = userRepository.save(product);
        kafkaProducerService.send(topic, KeySet.UPDATE, jsonMapper.writeValue(saved));
        return saved;
    }

    @Override
    public User findOne(UUID id) {
        LOGGER.info("Find user, id: {}", id);
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Find all users");
        Iterable<User> products = userRepository.findAll();
        return IteratorUtils.toList(products.iterator());
    }

    @Override
    public void delete(UUID id) {
        LOGGER.info("Delete user, id: {}", id);
        userRepository.delete(id);
        kafkaProducerService.send(topic, KeySet.DELETE, id.toString());
    }

}
