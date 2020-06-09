package com.example.springbootexperiment.response;

import com.example.springbootexperiment.model.ContentImage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Content {
    protected String type;
    protected String title;
    private List<ContentImage> images;
}
