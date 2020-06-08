package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.Teledrama;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeledramaRepository extends MongoRepository<Teledrama, String> {

}
