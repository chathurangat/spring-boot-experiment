package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {

}
