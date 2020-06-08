package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("category_contents")
public class CategoryContent {

    @Id
    private String id;

    private Integer sequenceNo;

    @DBRef(lazy = true)
    private Category category;

    @DBRef(lazy = true)
    private Content content;
}
