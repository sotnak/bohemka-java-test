package com.antospa.rest.item;

public enum OrderType {
    ascendant("ASC"),
    descendant("DES");

    private final String value;

    OrderType(String str){
        this.value = str;
    }

    public static OrderType getOrderType(String o) throws Exception {
        return switch (o) {
            case "ascendant" -> OrderType.ascendant;
            case "descendant" -> OrderType.descendant;
            default -> throw new Exception("Unknown order type");
        };
    }

    public String getValue() {
        return this.value;
    }
}
