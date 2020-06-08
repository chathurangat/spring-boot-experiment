package com.example.springbootexperiment.controller;

import com.example.springbootexperiment.model.Category;
import com.example.springbootexperiment.model.Screen;
import com.example.springbootexperiment.model.ScreenCategory;
import com.example.springbootexperiment.repository.CategoryRepository;
import com.example.springbootexperiment.repository.ScreenCategoryRepository;
import com.example.springbootexperiment.repository.ScreenRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class ScreenController {

    private final ScreenRepository screenRepository;
    private final CategoryRepository categoryRepository;
    private final ScreenCategoryRepository screenCategoryRepository;

    @Autowired
    public ScreenController(ScreenRepository screenRepository,
                            CategoryRepository categoryRepository,
                            ScreenCategoryRepository screenCategoryRepository) {
        this.screenRepository = screenRepository;
        this.categoryRepository = categoryRepository;
        this.screenCategoryRepository = screenCategoryRepository;
    }
//
//    @GetMapping("/initialize")
//    public void create() {
//
//        List<Category> categoryList = new ArrayList<>();
//
//        for (int i = 1; i < 5; i++) {
//            Category category = new Category();
//            category.setName("category " + i);
//            Category createdCategory = categoryRepository.save(category);
//            categoryList.add(createdCategory);
//        }
//
//        Screen screen = new Screen();
//        screen.setName("screen1");
//        Screen createdScreen = screenRepository.save(screen);
//
//        List<ScreenCategory> screenCategoryList = new ArrayList<>();
//
//        categoryList.forEach(category -> {
//            ScreenCategory screenCategory = new ScreenCategory();
//            screenCategory.setCategory(category);
//            screenCategory.setScreen(screen);
//            screenCategory.setSequenceNumber(new Random().nextInt(10));
//            ScreenCategory screenCategoryCreated = screenCategoryRepository.save(screenCategory);
//            screenCategoryList.add(screenCategoryCreated);
//
//            //adding screenCategory to category
//            category.getScreens().add(screenCategory);
//            categoryRepository.save(category);
//        });
//
//        //adding list of categories to screen
//        createdScreen.setCategories(screenCategoryList);
//        screenRepository.save(createdScreen);
//
//    }


    @PostMapping("/screens")
    public void create() {

        List<Category> categoryList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            Category category = new Category();
            category.setName("category " + i);
            Category createdCategory = categoryRepository.save(category);
            categoryList.add(createdCategory);
        }

        Screen screen = new Screen();
        screen.setName("screen1");
        Screen createdScreen = screenRepository.save(screen);

        List<ScreenCategory> screenCategoryList = new ArrayList<>();

        categoryList.forEach(category -> {
            ScreenCategory screenCategory = new ScreenCategory();
            screenCategory.setCategory(category);
            screenCategory.setScreen(screen);
            screenCategory.setSequenceNumber(new Random().nextInt(10));
            ScreenCategory screenCategoryCreated = screenCategoryRepository.save(screenCategory);
            screenCategoryList.add(screenCategoryCreated);

//            //adding screenCategory to category
//            category.getScreens().add(screenCategory);
//            categoryRepository.save(category);
        });

        //adding list of categories to screen
        createdScreen.setCategories(screenCategoryList);
        screenRepository.save(createdScreen);
    }

    @GetMapping("/screens/{screen-id}")
    public void getScreen(@PathVariable("screen-id") String screenId) {
        Optional<Screen> screenOptional = screenRepository.findById(screenId);
        if (screenOptional.isPresent()) {
            System.out.println("==== getting the categories");
            List<String> screenCategoryIds = new ArrayList<>();
            screenOptional.get().getCategories().forEach(screenCategory -> {
                screenCategoryIds.add(screenCategory.getId());
            });

            System.out.println("====screen category ids " + screenCategoryIds.size());

            Sort sort = Sort.by(Sort.Direction.DESC, "sequenceNumber");
            List<ScreenCategory> screenCategoriesFound = screenCategoryRepository.findAllCategoriesByScreenId(screenId, sort, 2);

            System.out.println(screenCategoriesFound.size());
            screenCategoriesFound.forEach(screenCategory -> {
                System.out.println("***** " + screenCategory.getSequenceNumber());
            });
        }
    }
}
