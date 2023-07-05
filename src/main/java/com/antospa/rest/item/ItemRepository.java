package com.antospa.rest.item;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    //@Query("{_id: ObjectId('?0')}")
    //Optional<Item> findById(String id);
}
