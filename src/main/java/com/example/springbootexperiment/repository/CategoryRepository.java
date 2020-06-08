package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}