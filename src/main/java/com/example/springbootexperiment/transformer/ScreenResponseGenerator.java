package com.example.springbootexperiment.transformer;


import com.example.springbootexperiment.model.CategoryContent;
import com.example.springbootexperiment.model.Screen;
import com.example.springbootexperiment.response.Category;
import com.example.springbootexperiment.response.Content;
import com.example.springbootexperiment.response.HomeScreenData;
import com.example.springbootexperiment.response.HomeScreenResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ScreenResponseGenerator {

    public static HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {

        long start = System.currentTimeMillis();
        List<HomeScreenData> homeScreenData = screens.stream().map(screen ->
                HomeScreenData.builder()
                        .id(screen.getId())
                        .name(screen.getName())
                        .build()
        ).collect(Collectors.toList());

        Optional<List<Category>> categoriesOptional = screens.parallelStream().map(screen ->
                screen.getCategories()
                        .stream()
                        .map(category -> Category.builder().id(category.getCategory().getId())
                                .name(category.getCategory().getName())
                                .sequenceNo(category.getSequenceNumber()).contents(
                                        categoryContents.parallelStream().filter(categoryContent ->
                                                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
                                                .map(categoryContent ->
                                                        Content.builder().type(categoryContent.getContent().getType())
                                                                .title(categoryContent.getContent().getTitle())
                                                                .build())
                                                .collect(Collectors.toList())
                                ).build()
                        ).collect(Collectors.toList())
        ).collect(Collectors.toList()).stream().findFirst();

        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
        HomeScreenData homeScreenData2 = homeScreenData.stream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
        homeScreenData2.setCategories(categoriesOptional.get());
        homeScreenResponse.setData(homeScreenData);

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("====time " + executionTime);

        return homeScreenResponse;
    }


}
