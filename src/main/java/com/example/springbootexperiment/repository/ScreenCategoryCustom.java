package com.example.springbootexperiment.repository;

import com.example.springbootexperiment.model.ScreenCategory;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ScreenCategoryCustom {

    List<ScreenCategory> findAllCategoriesByScreenId(String screenId, Sort sort, Integer limit);
}
