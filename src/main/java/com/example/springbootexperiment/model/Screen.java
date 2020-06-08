package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("screens")
public class Screen {

    @Id
    private String id;
    private String name;

    @DBRef(lazy = true)
    private List<ScreenCategory> categories;
}
