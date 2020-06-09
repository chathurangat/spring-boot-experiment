package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.CategoryContent;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryContentRepository extends MongoRepository<CategoryContent, String> {

    @Query(value="{}")
    List<CategoryContent> findAllByCategory(List<String> id, Sort sort);

}
