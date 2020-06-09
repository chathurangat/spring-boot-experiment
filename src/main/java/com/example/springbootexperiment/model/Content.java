package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "contents")
public class Content {

    @Id
    private String id;
    protected String type;
    protected String title;
    private List<ContentImage> images;
}
