package com.antospa.rest.item.enums;

public enum OrderType {
    ascendant(1),
    descendant(-1);

    private final Integer value;

    OrderType(Integer val){
        this.value = val;
    }

    public static OrderType getOrderType(String o) throws Exception {
        return switch (o) {
            case "ascendant" -> OrderType.ascendant;
            case "descendant" -> OrderType.descendant;
            default -> throw new Exception("Unknown order type");
        };
    }

    public Integer getValue() {
        return this.value;
    }
}
