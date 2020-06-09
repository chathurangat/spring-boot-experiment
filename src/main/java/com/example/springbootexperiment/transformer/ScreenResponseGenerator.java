package com.example.springbootexperiment.transformer;


import com.example.springbootexperiment.model.CategoryContent;
import com.example.springbootexperiment.model.Screen;
import com.example.springbootexperiment.response.Category;
import com.example.springbootexperiment.response.Content;
import com.example.springbootexperiment.response.HomeScreenData;
import com.example.springbootexperiment.response.HomeScreenResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenResponseGenerator {

    public static HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents,String menuId) {
        List<HomeScreenData> homeScreenData = new ArrayList<>();
        screens.forEach(screen -> {
                    homeScreenData.add(
                            HomeScreenData.builder()
                                    .id(screen.getId())
                                    .name(screen.getName())
                                    .build()

                    );
                }
        );
        Map<String, List<Content>> listMap = new HashMap<>();
        categoryContents.forEach(categoryContent -> {
            String categoryId = categoryContent.getCategory().getId();
            if (listMap.get(categoryId) == null) {
                listMap.put(categoryId, new ArrayList<Content>());
            }
            List<Content> list = listMap.get(categoryId);
            list.add(Content.builder()
                    .title(categoryContent.getContent().getTitle())
                    .type(categoryContent.getContent().getType())
//                    .images(categoryContent.getContent().getImages())
                    .build());
        });
        List<Category> categories = new ArrayList<>();

        categoryContents.forEach(categoryContent -> {
            categories.add(
                    Category.builder()
                            .id(categoryContent.getCategory().getId())
                            .name(categoryContent.getCategory().getName())
                            .sequenceNo(categoryContent.getSequenceNo())
                            .contents(listMap.get(categoryContent.getCategory().getId()))
                            .build()
            );
        });
        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
        HomeScreenData homeScreenData2 = homeScreenData.stream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
        homeScreenData2.setCategories(categories);
        homeScreenData.add(homeScreenData2);
        homeScreenResponse.setData(homeScreenData);
        return homeScreenResponse;
    }


}
