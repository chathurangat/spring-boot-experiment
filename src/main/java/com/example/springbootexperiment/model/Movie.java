package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("movies")
public class Movie {

    /*public Movie() {
        this.type = "movie";
    }*/

    @Id
    private String id;
    private String producer;
    private List<ContentImage> images;
}
