package com.example.springbootexperiment.transformer;

import com.example.springbootexperiment.model.CategoryContent;
import com.example.springbootexperiment.model.Screen;
import com.example.springbootexperiment.response.Category;
import com.example.springbootexperiment.response.Content;
import com.example.springbootexperiment.response.HomeScreenData;
import com.example.springbootexperiment.response.HomeScreenResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScreenResponseGenerator {

    public HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {
        List<HomeScreenData> homeScreenData = screens.parallelStream().map(screen ->
                HomeScreenData.builder()
                        .id(screen.getId())
                        .name(screen.getName())
                        .build()
        ).collect(Collectors.toList());

        Screen homeScreen = screens.parallelStream().filter(screen -> screen.getId().equals(menuId)).findFirst().get();

        Map<String, List<Content>> contentsMap = categoryContents.parallelStream()
                .map(categoryContent ->
                        Content.builder().type(categoryContent.getContent().getType())
                                .categoryId(categoryContent.getCategory().getId())
                                .title(categoryContent.getContent().getTitle())
                                .images(categoryContent.getContent().getImages())
                                .build())
                .collect(Collectors.groupingBy(Content::getCategoryId));

        List<Category> categories = homeScreen.getCategories().parallelStream()
                .map(category -> Category.builder().id(category.getCategory().getId())
                        .name(category.getCategory().getName())
                        .sequenceNo(category.getSequenceNumber()).contents(
                                contentsMap.get(category.getCategory().getId())
                        ).build()
                ).collect(Collectors.toList());

        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
        HomeScreenData homeScreenData2 = homeScreenData.parallelStream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
        homeScreenData2.setCategories(categories);
        homeScreenResponse.setData(homeScreenData);
        return homeScreenResponse;
    }
}