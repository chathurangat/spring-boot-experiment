package com.example.springbootexperiment.response;

import com.example.springbootexperiment.model.ContentImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Content {
    @JsonIgnore
    protected String categoryId;
    protected String type;
    protected String title;
    private List<ContentImage> images;
}
