package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.ScreenCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ScreenCategoryRepository extends CrudRepository<ScreenCategory, String>, ScreenCategoryCustom {

    Iterable<ScreenCategory> findAllById(Iterable<String> var1, Sort sort);
}
