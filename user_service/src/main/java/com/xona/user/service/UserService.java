package com.xona.user.service;

import com.xona.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User save(User user);

    User update(UUID id, User user);

    User findOne(UUID id);

    List<User> findAll();

    void delete(UUID id);

}
