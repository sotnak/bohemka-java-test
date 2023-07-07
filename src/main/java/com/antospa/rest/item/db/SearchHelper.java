package com.antospa.rest.item.db;

import com.antospa.rest.item.Item;
import com.antospa.rest.item.enums.Enums;
import com.antospa.rest.item.enums.Operator;
import com.antospa.rest.item.enums.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchHelper {
    @Autowired
    ItemRepository itemRepository;

    public Enums getEnums(String operator, String orderType) throws Exception {

        Operator o = Operator.getOperator(operator);
        OrderType ot = OrderType.getOrderType(orderType);

        return new Enums(o, ot);
    }

    public ArrayList<Item> search(Enums enums, ArrayList<String> tags, Integer value, String orderBy, Integer limit, Integer page){
        Integer skip = limit * page;

        if(tags != null && value != null)
            return itemRepository.searchByTagsAndValue(tags, enums.operator.getValue(), value, orderBy, enums.orderType.getValue(), skip, limit);
        if(tags != null)
            return itemRepository.searchByTags(tags, orderBy, enums.orderType.getValue(), skip, limit);
        if(value != null)
            return itemRepository.searchByValue(enums.operator.getValue(), value, orderBy, enums.orderType.getValue(), skip, limit);

        return itemRepository.sortAndPage(orderBy, enums.orderType.getValue(), skip, limit);
    }
}
