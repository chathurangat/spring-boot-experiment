package com.example.springbootexperiment.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("movies")
public class Movie extends Content {

    public Movie() {
        this.type = "movie";
    }

    private String producer;
    private List<ContentImage> images;
}
