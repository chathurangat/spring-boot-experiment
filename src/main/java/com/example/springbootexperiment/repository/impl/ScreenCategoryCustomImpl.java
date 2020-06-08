package com.example.springbootexperiment.repository.impl;

import com.example.springbootexperiment.model.ScreenCategory;
import com.example.springbootexperiment.repository.ScreenCategoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenCategoryCustomImpl implements ScreenCategoryCustom {

    private final MongoOperations mongoOperations;

    @Autowired
    public ScreenCategoryCustomImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<ScreenCategory> findAllCategoriesByScreenId(String screenId, Sort sort, Integer limit) {
        //            Query clientData = new Query(Criteria.where("screen.id").is(screenId));
//            clientData.with(Sort.by(Sort.Direction.ASC, "sequenceNumber"));
//            clientData.limit(10);
//            List<ScreenCategory> screenCategoriesFound = mongoOperations.find(clientData, ScreenCategory.class);

        Criteria find = Criteria.where("screen.id").is(screenId);
        Query query = new Query().addCriteria(find)
                .with(sort)
                .limit(limit);
        return mongoOperations.find(query, ScreenCategory.class);
    }
}


