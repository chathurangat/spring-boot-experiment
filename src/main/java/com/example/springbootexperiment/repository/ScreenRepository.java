package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.Screen;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScreenRepository extends MongoRepository<Screen, String> {

}
