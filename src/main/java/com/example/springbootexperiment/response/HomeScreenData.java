package com.example.springbootexperiment.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class HomeScreenData {

    private String id;
    private String name;
    private List<Category> categories;

}
