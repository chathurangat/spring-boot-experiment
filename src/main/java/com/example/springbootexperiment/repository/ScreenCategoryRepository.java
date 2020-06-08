package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.ScreenCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScreenCategoryRepository extends CrudRepository<ScreenCategory, String>, ScreenCategoryCustom {

    @Query(value = "{}")
    List<ScreenCategory> findAllById(Iterable<String> var1, Sort sort);

    @Query(value = "{}")
    Page<ScreenCategory> findAllById(Iterable<String> var1, Pageable pageable);
}
