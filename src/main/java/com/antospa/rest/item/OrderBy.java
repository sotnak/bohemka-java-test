package com.antospa.rest.item;

public enum OrderBy {
    value("value"),
    updateTimestamp("updateTimestamp");

    private final String inner_value;

    OrderBy(String str){
        this.inner_value = str;
    }

    public static OrderBy getOrderType(String o) throws Exception {
        if(o == null)
            return null;

        return switch (o) {
            case "value" -> OrderBy.value;
            case "updateTimestamp" -> OrderBy.updateTimestamp;
            default -> throw new Exception("Unknown order type");
        };
    }

    public String getValue() {
        return this.inner_value;
    }
}
