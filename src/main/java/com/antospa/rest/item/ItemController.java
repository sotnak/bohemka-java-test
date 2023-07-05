package com.antospa.rest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    private final Logger logger = Logger.getLogger(ItemController.class.getName());

    @PostMapping("/item")
    public Item createItem(@RequestBody Item item){
        item.setUpdatedAt(new Date());
        logger.log(Level.INFO, "POST /item " + item.toString());

        return itemRepository.insert(item);
    }

    @PutMapping("/item/{id}")
    public Item updateItem(@PathVariable String id, @RequestBody Item item){
        item.setUpdatedAt(new Date());
        logger.log(Level.INFO, "PUT /item/" + id + " " + item.toString());

        item.setId(id);
        return itemRepository.save(item);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable String id){
        logger.log(Level.INFO, "GET /item/" + id);
        Optional<Item> item = itemRepository.findById(id);

        return ResponseEntity.of(item);
    }

    @GetMapping("/item")
    public ArrayList<Item> getItems(@RequestParam(required = false, defaultValue = "") ArrayList<String> tags,
                                    @RequestParam(required = false) Integer value,
                                    @RequestParam(required = false, defaultValue = "=") String operator,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false) Integer limit,
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

        try {
            Operator o = Operator.getOperator(operator);
            OrderType ot = OrderType.getOrderType(orderType);
            OrderBy ob = OrderBy.getOrderType(orderBy);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return new ArrayList<>();
    }
}
