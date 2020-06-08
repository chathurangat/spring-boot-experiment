package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("screen_categories")
public class ScreenCategory {

    @Id
    private String id;

    private Integer sequenceNumber;

    @DBRef(lazy = true)
    private Screen screen;

    @DBRef(lazy = true)
    private Category category;
}
