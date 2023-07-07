package com.antospa.rest.item.db;

import com.antospa.rest.item.Item;
import com.antospa.rest.item.configuration.MongoConfiguration;
import com.antospa.rest.item.enums.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class RepositoryService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SearchHelper searchHelper;

    @Autowired
    MongoConfiguration mongoConfiguration;

    private Item setupDates(Item item){
        item.setUpdatedAt(Date.from(Instant.now()));
        item.setExpireAt(Date.from(Instant.now().plusSeconds(mongoConfiguration.expireTime)));
        return item;
    }

    public ArrayList<Item> search(ArrayList<String> tags, Integer value, String operator, String orderBy, String orderType, Integer limit, Integer page) throws Exception {
        Enums enums = null;

        ArrayList<String> t = null;
        if(tags.size() > 0)
            t = tags;

        enums = searchHelper.getEnums(operator, orderType);

        return searchHelper.search(enums, t, value, orderBy, limit, page);
    }

    public Optional<Item> findById(String id){
        return itemRepository.findById(id);
    }

    public void delete(String id){
        itemRepository.deleteById(id);
    }

    public Item update(String id, Item item){
        item.setId(id);
        this.setupDates(item);
        return itemRepository.save(item);
    }

    public Item create(Item item){
        this.setupDates(item);
        return itemRepository.insert(item);
    }
}
