package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("categories")
public class Category {

    private String id;
    private String name;
    private List<ScreenCategory> screens = new ArrayList<>();
}
