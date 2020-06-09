package com.example.springbootexperiment.transformer;

//import com.example.springbootexperiment.model.Category;

import com.example.springbootexperiment.model.CategoryContent;
import com.example.springbootexperiment.model.Screen;
import com.example.springbootexperiment.response.Category;
import com.example.springbootexperiment.response.Content;
import com.example.springbootexperiment.response.HomeScreenData;
import com.example.springbootexperiment.response.HomeScreenResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScreenResponseGenerator {

////    @Cacheable(cacheNames = "homeCache", key = "#menuId")
//    public HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {
//        List<HomeScreenData> homeScreenData = screens.parallelStream().map(screen ->
//                HomeScreenData.builder()
//                        .id(screen.getId())
//                        .name(screen.getName())
//                        .build()
//        ).collect(Collectors.toList());
//
////        Optional<List<Category>> categoriesOptional = screens.parallelStream().map(screen ->
////                screen.getCategories()
////                        .stream()
////                        .map(category -> Category.builder().id(category.getCategory().getId())
////                                .name(category.getCategory().getName())
////                                .sequenceNo(category.getSequenceNumber()).contents(
////                                        categoryContents.parallelStream().filter(categoryContent ->
////                                                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
////                                                .map(categoryContent ->
////                                                        Content.builder().type(categoryContent.getContent().getType())
////                                                                .title(categoryContent.getContent().getTitle())
////                                                                .build())
////                                                .collect(Collectors.toList())
////                                ).build()
////                        ).collect(Collectors.toList())
////        ).collect(Collectors.toList()).stream().findFirst();
//
//        List<List<Category>> categories = screens.parallelStream().map(screen ->
//                screen.getCategories()
//                        .stream()
//                        .map(category -> Category.builder().id(category.getCategory().getId())
//                                .name(category.getCategory().getName())
//                                .sequenceNo(category.getSequenceNumber()).contents(
//                                        categoryContents.parallelStream().filter(categoryContent ->
//                                                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
//                                                .map(categoryContent ->
//                                                        Content.builder().type(categoryContent.getContent().getType())
//                                                                .title(categoryContent.getContent().getTitle())
//                                                                .build())
//                                                .collect(Collectors.toList())
//                                ).build()
//                        ).collect(Collectors.toList())
//        ).collect(Collectors.toList());
//
//        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
//        HomeScreenData homeScreenData2 = homeScreenData.parallelStream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
//        homeScreenData2.setCategories(categories.get(0));
//        homeScreenResponse.setData(homeScreenData);
//        return homeScreenResponse;
//    }


//    //    @Cacheable(cacheNames = "homeCache", key = "#menuId")
//    public HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {
//        System.out.println("===generating");
//        List<HomeScreenData> homeScreenData = screens.parallelStream().map(screen ->
//                HomeScreenData.builder()
//                        .id(screen.getId())
//                        .name(screen.getName())
//                        .build()
//        ).collect(Collectors.toList());
//
//        Screen homeScreen = screens.parallelStream().filter(screen -> screen.getId().equals(menuId)).findFirst().get();
//
//        List<Category> categories = homeScreen.getCategories().parallelStream()
//                .map(category -> Category.builder().id(category.getCategory().getId())
//                        .name(category.getCategory().getName())
//                        .sequenceNo(category.getSequenceNumber()).contents(
//                                categoryContents.parallelStream().filter(categoryContent ->
//                                        categoryContent.getCategory().getId().equals(category.getCategory().getId()))
//                                        .map(categoryContent ->
//                                                Content.builder().type(categoryContent.getContent().getType())
//                                                        .title(categoryContent.getContent().getTitle())
//                                                        .build())
//                                        .collect(Collectors.toList())
//                        ).build()
//                ).collect(Collectors.toList());
//
//        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
//        HomeScreenData homeScreenData2 = homeScreenData.parallelStream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
//        homeScreenData2.setCategories(categories);
//        homeScreenResponse.setData(homeScreenData);
//        return homeScreenResponse;
//    }


    //    @Cacheable(cacheNames = "homeCache", key = "#menuId")
    public HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {
        System.out.println("===generating");
        List<HomeScreenData> homeScreenData = screens.parallelStream().map(screen ->
                HomeScreenData.builder()
                        .id(screen.getId())
                        .name(screen.getName())
                        .build()
        ).collect(Collectors.toList());

        Screen homeScreen = screens.parallelStream().filter(screen -> screen.getId().equals(menuId)).findFirst().get();

//        List<Content> contentList = categoryContents.parallelStream()
//                .map(categoryContent ->
//                        Content.builder().type(categoryContent.getContent().getType())
//                                .title(categoryContent.getContent().getTitle())
//                                .build())
//                .collect(Collectors.toList());

        Map<String, List<Content>> contentsMap = categoryContents.parallelStream()
                .map(categoryContent ->
                        Content.builder().type(categoryContent.getContent().getType())
                                .categoryId(categoryContent.getCategory().getId())
                                .title(categoryContent.getContent().getTitle())
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

//                          categoryContents.parallelStream().filter(categoryContent ->
//            categoryContent.getCategory().getId().equals(category.getCategory().getId()))
//            .map(categoryContent ->
//            Content.builder().type(categoryContent.getContent().getType())
//            .title(categoryContent.getContent().getTitle())
//            .build())
//            .collect(Collectors.toList())

//    public HomeScreenResponse generate(List<Screen> screens, List<CategoryContent> categoryContents, String menuId) {
//        List<HomeScreenData> homeScreenData = screens.parallelStream().map(screen ->
//                HomeScreenData.builder()
//                        .id(screen.getId())
//                        .name(screen.getName())
//                        .build()
//        ).collect(Collectors.toList());
//
////        categoryContents.parallelStream().filter(categoryContent ->
////                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
////                .map(categoryContent ->
////                        Content.builder().type(categoryContent.getContent().getType())
////                                .title(categoryContent.getContent().getTitle())
////                                .build())
////                .collect(Collectors.toList());
//
//        Map<Category, List<CategoryContent>> categoryContentMap = categoryContents.parallelStream().collect(Collectors.groupingBy(CategoryContent::getCategory));
//
//        Map<String, List<Content>> contents = new HashMap<>();
//
//        categoryContentMap.forEach((category, categoryContentList) -> {
//            List<Content> contentList = categoryContentList.stream().map(categoryContent -> Content.builder().type(categoryContent.getContent().getType()).title(categoryContent.getContent().getTitle()).build()).collect(Collectors.toList());
//            contents.put(category.getId(), contentList);
//        });
//
////        categoryContentMap.entrySet().stream()..map(entry -> entry.getValue().stream().map(categoryContent -> Content.builder().build()).collect(Collectors.toList()));
//
////      screens.parallelStream().map(screen -> screen.getCategories().stream().map(screenCategory -> categoryContentMap.get(screenCategory.getCategory())));
//
////        System.out.println(categoryContentMap);
//
//
////                Optional<List<com.example.springbootexperiment.response.Category>> categoriesOptional = screens.parallelStream().map(screen ->
////                screen.getCategories()
////                        .stream()
////                        .map(category -> com.example.springbootexperiment.response.Category.builder().id(category.getCategory().getId())
////                                .name(category.getCategory().getName())
////                                .sequenceNo(category.getSequenceNumber()).contents(
////                                        categoryContentMap.get(category.getCategory()).stream().map(categoryContent -> Content.builder().type(categoryContent.getContent().getType()).title(categoryContent.getContent().getTitle()).build()).collect(Collectors.toList())
////                                ).build()
////                        ).collect(Collectors.toList())
////        ).collect(Collectors.toList()).stream().findFirst();
//
////        Optional<List<com.example.springbootexperiment.response.Category>> categoriesOptional = screens.parallelStream().map(screen ->
////                screen.getCategories()
////                        .stream()
////                        .map(category -> com.example.springbootexperiment.response.Category.builder().id(category.getCategory().getId())
////                                .name(category.getCategory().getName())
////                                .sequenceNo(category.getSequenceNumber()).contents(
////                                        categoryContentMap.get(category.getCategory()).stream().map(categoryContent -> Content.builder().type(categoryContent.getContent().getType()).title(categoryContent.getContent().getTitle()).build()).collect(Collectors.toList())
////                                ).build()
////                        ).collect(Collectors.toList())
////        ).collect(Collectors.toList()).stream().findFirst();
//
//        Optional<List<com.example.springbootexperiment.response.Category>> categoriesOptional = screens.parallelStream().map(screen ->
//                screen.getCategories()
//                        .stream()
//                        .map(category -> com.example.springbootexperiment.response.Category.builder().id(category.getCategory().getId())
//                                .name(category.getCategory().getName())
//                                .sequenceNo(category.getSequenceNumber()).contents(new ArrayList<>()).build()
//                        ).collect(Collectors.toList())
//        ).collect(Collectors.toList()).stream().findFirst();
//
//
////        Optional<List<com.example.springbootexperiment.response.Category>> categoriesOptional = screens.parallelStream().map(screen ->
////                screen.getCategories()
////                        .stream()
////                        .map(category -> com.example.springbootexperiment.response.Category.builder().id(category.getCategory().getId())
////                                .name(category.getCategory().getName())
////                                .sequenceNo(category.getSequenceNumber()).contents(
////                                        categoryContents.parallelStream().filter(categoryContent ->
////                                                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
////                                                .map(categoryContent ->
////                                                        Content.builder().type(categoryContent.getContent().getType())
////                                                                .title(categoryContent.getContent().getTitle())
////                                                                .build())
////                                                .collect(Collectors.toList())
////                                ).build()
////                        ).collect(Collectors.toList())
////        ).collect(Collectors.toList()).stream().findFirst();
//
//
////        Optional<List<Category>> categoriesOptional = screens.parallelStream().map(screen ->
////                screen.getCategories()
////                        .stream()
////                        .map(category -> Category.builder().id(category.getCategory().getId())
////                                .name(category.getCategory().getName())
////                                .sequenceNo(category.getSequenceNumber()).contents(
////                                        categoryContents.parallelStream().filter(categoryContent ->
////                                                categoryContent.getCategory().getId().equals(category.getCategory().getId()))
////                                                .map(categoryContent ->
////                                                        Content.builder().type(categoryContent.getContent().getType())
////                                                                .title(categoryContent.getContent().getTitle())
////                                                                .build())
////                                                .collect(Collectors.toList())
////                                ).build()
////                        ).collect(Collectors.toList())
////        ).collect(Collectors.toList()).stream().findFirst();
//
//        HomeScreenResponse homeScreenResponse = new HomeScreenResponse();
//        HomeScreenData homeScreenData2 = homeScreenData.parallelStream().filter(homeScreenData1 -> homeScreenData1.getId().equals(menuId)).findFirst().get();
////        homeScreenData2.setCategories(categoriesOptional.get());
//        homeScreenResponse.setData(homeScreenData);
//        return homeScreenResponse;
//    }
}
