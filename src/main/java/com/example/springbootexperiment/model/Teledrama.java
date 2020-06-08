package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("teledramas")
public class Teledrama extends Content {

    public Teledrama() {
        this.type = "teledrama";
    }

    private String director;
}
