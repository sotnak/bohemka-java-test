package com.antospa.rest.item;

import com.antospa.rest.item.db.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ItemController {

    @Autowired
    RepositoryService repositoryService;

    private final Logger logger = Logger.getLogger(ItemController.class.getName());

    @PostMapping("/item")
    public Item createItem(@RequestBody Item item){
        logger.log(Level.INFO, "POST /item " + item.toString());

        return repositoryService.create(item);
    }

    @PutMapping("/item/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody Item item){
        logger.log(Level.INFO, "PUT /item/" + id + " " + item.toString());

        return repositoryService.update(id, item);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity deleteItem(@PathVariable String id){
        logger.log(Level.INFO, "DELETE /item/" + id);
        repositoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String id){
        logger.log(Level.INFO, "GET /item/" + id);

        return ResponseEntity.of(repositoryService.findById(id));
    }

    @GetMapping("/item")
    @Cacheable(value = "itemSearch")
    public ArrayList<Item> getItems(@RequestParam(required = false, defaultValue = "") ArrayList<String> tags,
                                    @RequestParam(required = false) Integer value,
                                    @RequestParam(required = false, defaultValue = "=") String operator,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "1000") Integer limit,
                                    @RequestParam(required = false) String orderBy,
                                    @RequestParam(required = false, defaultValue = "ascendant") String orderType
    ) {

        logger.log(Level.INFO, "GET /item tags: " + tags.toString() +
                ", value: " + value +
                ", operator: " + operator +
                ", orderrBy: " + orderBy +
                ", orderType: " + orderType +
                ", page: " + page +
                ", limit: " + limit);

        ArrayList<Item> res;

        try {
            res = repositoryService.search(tags, value, operator, orderBy, orderType, limit, page);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return res;
    }
}
