package com.example.springbootexperiment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("teledramas")
public class Teledrama {

    /*public Teledrama() {
        this.type = "teledrama";
    }*/
    @Id
    private String id;
    private String director;
}
