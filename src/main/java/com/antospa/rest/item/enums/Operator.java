package com.antospa.rest.item.enums;

public enum Operator {
    equal("$eq"),
    more("$gt"),
    less("$lt");

    private final String value;

    Operator(String str){
        this.value = str;
    }

    public static Operator getOperator(String o) throws Exception {
        return switch (o) {
            case "=" -> Operator.equal;
            case "<" -> Operator.more;
            case ">" -> Operator.less;
            default -> throw new Exception("Unknown operator");
        };
    }

    public String getValue() {
        return this.value;
    }
}
