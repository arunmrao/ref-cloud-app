package com.xona.consumer.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
        this.id = UUID.randomUUID();
    }

}
