package com.antospa.rest.item.db;

import com.antospa.rest.item.Item;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    @Aggregation(pipeline = {
            "{ $match: {'value': {?0:  ?1}} }",
            "{ $sort:  {'?2': ?3 }}",
            "{$skip:  ?4}",
            "{$limit:  ?5}"
    })
    ArrayList<Item> searchByValue(String operator, Integer value, String orderBy, Integer orderType, Integer skip, Integer page);


    @Aggregation(pipeline = {
            "{ $match: {'tag': {$in:  ?0}} }",
            "{ $sort:  {'?1': ?2 }}",
            "{$skip:  ?3}",
            "{$limit:  ?4}"
    })
    ArrayList<Item> searchByTags(ArrayList<String> tags, String orderBy, Integer orderType, Integer skip, Integer page);


    @Aggregation(pipeline = {
            "{ $match: {'tag': {$in:  ?0}, 'value': {?1:  ?2}} }",
            "{ $sort:  {'?3': ?4 }}",
            "{$skip:  ?5}",
            "{$limit:  ?6}"
    })
    ArrayList<Item> searchByTagsAndValue(ArrayList<String> tags, String operator, Integer value, String orderBy, Integer orderType, Integer skip, Integer page);

    @Aggregation(pipeline = {
            "{ $sort:  {'?0': ?1 }}",
            "{$skip:  ?2}",
            "{$limit:  ?3}"
    })
    ArrayList<Item> sortAndPage(String orderBy, Integer orderType, Integer skip, Integer page);

}
