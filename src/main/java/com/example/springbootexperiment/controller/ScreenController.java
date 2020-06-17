package com.example.springbootexperiment.controller;

import com.example.springbootexperiment.model.*;
import com.example.springbootexperiment.response.HomeScreenResponse;
import com.example.springbootexperiment.repository.*;
import com.example.springbootexperiment.transformer.ScreenResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ScreenController {

    private final ScreenRepository screenRepository;
    private final CategoryRepository categoryRepository;
    private final ScreenCategoryRepository screenCategoryRepository;
    private final MovieRepository movieRepository;
    private final TeledramaRepository teledramaRepository;
    private final CategoryContentRepository categoryContentRepository;
    private final ScreenResponseGenerator screenResponseGenerator;

    @Autowired
    public ScreenController(ScreenRepository screenRepository,
                            CategoryRepository categoryRepository,
                            ScreenCategoryRepository screenCategoryRepository,
                            MovieRepository movieRepository,
                            TeledramaRepository teledramaRepository,
                            CategoryContentRepository categoryContentRepository,
                            ScreenResponseGenerator screenResponseGenerator) {
        this.screenRepository = screenRepository;
        this.categoryRepository = categoryRepository;
        this.screenCategoryRepository = screenCategoryRepository;
        this.movieRepository = movieRepository;
        this.teledramaRepository = teledramaRepository;
        this.categoryContentRepository = categoryContentRepository;
        this.screenResponseGenerator = screenResponseGenerator;
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
//            System.out.println("==== getting the categories");
            List<String> screenCategoryIds = new ArrayList<>();
            screenOptional.get().getCategories().forEach(screenCategory -> {
                screenCategoryIds.add(screenCategory.getId());
            });

//            List<ScreenCategory> res = screenCategoryRepository.findAllById(screenCategoryIds, Sort.by(Sort.Direction.ASC, "sequenceNumber"));

            Pageable pageable = PageRequest.of(0, 3, Sort.by("sequenceNumber"));
            Page<ScreenCategory> res = screenCategoryRepository.findAllById(screenCategoryIds, pageable);

            res.getContent().forEach(screenCategory -> {
                System.out.println("===screen seq " + screenCategory.getSequenceNumber());
            });

            System.out.println("total pages " + res.getTotalPages());
            System.out.println("total elements " + res.getTotalElements());
            System.out.println("next page " + res.nextPageable().getPageNumber());

//            System.out.println("====screen category ids " + screenCategoryIds.size());

            Sort sort = Sort.by(Sort.Direction.DESC, "sequenceNumber");
            List<ScreenCategory> screenCategoriesFound = screenCategoryRepository.findAllCategoriesByScreenId(screenId, sort, 2);

//            System.out.println(screenCategoriesFound.size());
//            screenCategoriesFound.forEach(screenCategory -> {
//                System.out.println("***** " + screenCategory.getSequenceNumber());
//            });

            Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(0, 2));
            System.out.println("categoryPage.getTotalPages() " + categoryPage.getTotalPages());
            System.out.println("categoryPage.getTotalElements() " + categoryPage.getTotalElements());
            System.out.println("categoryPage.nextPageable().getOffset() " + categoryPage.nextPageable().getOffset());
            System.out.println("nextPageable().getPageNumber() " + categoryPage.nextPageable().getPageNumber());

        }
    }


    @GetMapping("/contents")
    public void contents() {

        List<ContentImage> contentImages = new ArrayList<>();
        ContentImage contentImage = new ContentImage();
        contentImage.setType("thumbnail");
        contentImage.setUrl("www.google.lk");
        contentImages.add(contentImage);

        ContentImage contentImage2 = new ContentImage();
        contentImage2.setType("landscape");
        contentImage2.setUrl("www.google.lk");
        contentImages.add(contentImage2);

        for (int i = 0; i < 20; i++) {
            /*Movie movie = new Movie();
            movie.setTitle("Movie " + i);
            movie.setProducer("Producer");
            movie.setImages(contentImages);
            movieRepository.save(movie);

            Teledrama teledrama = new Teledrama();
            teledrama.setDirector("Director " + i);
            teledrama.setTitle("New Teledrama " + i);
            teledramaRepository.save(teledrama);*/
        }


    }

    @PostMapping("/categoryContents")
    public void createCategoryContent() {
        CategoryContent content = new CategoryContent();
        content.setSequenceNo(8);
        Category category = categoryRepository.findById("5edf0a9720d9e40ba8f6b304").get();

        content.setCategory(category);
//        Movie movie = movieRepository.findById("5edf0d4b74a8706821463a80").get();
        Teledrama movie = teledramaRepository.findById("5edf0d4b74a8706821463a81").get();
//        content.setContent(movie);
        CategoryContent categoryContent = categoryContentRepository.save(content);
       /* List<CategoryContent> categoryContents =category.getContents();
        categoryContents.add(categoryContent);
        category.setContents(categoryContents);
        categoryRepository.save(category);*/
    }

    @GetMapping("/screens")
    public HomeScreenResponse getScreen() {
        List<Screen> screens = screenRepository.findAll();
        Screen homeScreen = screens.parallelStream().filter(screen -> screen.getName().equals("Home")).findFirst().get();
        List<ScreenCategory> screenCategories = screenCategoryRepository.findAllByScreen(homeScreen.getId(), Sort.by(Sort.Direction.ASC, "sequenceNumber"));
        List<String> categoryIds = screenCategories.parallelStream().map(ScreenCategory::getId).collect(Collectors.toList());
        List<CategoryContent> categories = categoryContentRepository.findAllByCategory(categoryIds, Sort.by(Sort.Direction.ASC, "sequenceNumber"));
        return screenResponseGenerator.generate(screens, categories, homeScreen.getId());
    }

    @CrossOrigin
    @GetMapping(value = "/xml", produces = "application/xml")
    public String getXML() throws IOException {
        InputStream is = null;
        is = new FileInputStream("/source/file.txt");
//        is = new FileInputStream("/home/rmx-admin/workspace/OTT/spring-boot-experiment/src/main/java/com/example/springbootexperiment/controller/AdCampaig.txt");
        StringBuilder sb;
        try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
            String line = buf.readLine();
            sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        }
        String fileAsString = sb.toString();
        return fileAsString;
    }
}
