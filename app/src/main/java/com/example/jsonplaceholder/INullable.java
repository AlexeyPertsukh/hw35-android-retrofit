package com.example.jsonplaceholder;

public interface INullable {
    String EMPTY_STRING = "";

    default String getValueOrEmptyStringIfNull(String value) {
        return (value == null) ? EMPTY_STRING : value;

//        Optional<String> optional = Optional.ofNullable(value);   //не работает в старых api
//        return optional.orElse("");
    }
}
