package com.xona.product.entity;

import lombok.Data;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table
public class Product {

    @PrimaryKey
    private UUID id;

    @Column("description")
    private String description;

    @Column("price")
    private BigDecimal price;

    @Column("image_url")
    private String imageUrl;

    public Product() {
        this.id = UUID.randomUUID();
    }

}