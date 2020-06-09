package com.example.springbootexperiment.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {

    private String id;
    private String name;
    private Integer sequenceNo;
    private List<Content> contents;
}
