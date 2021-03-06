package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("categories")
public class Category {

    @Id
    private String id;
    
    private String name;

    private List<ScreenCategory> screens = new ArrayList<>();

    @DBRef(lazy = true)
    private List<CategoryContent> contents = new ArrayList<>();
}
