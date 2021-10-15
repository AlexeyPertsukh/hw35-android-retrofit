package jsonplaceholder_class;

public interface INull {
    String EMPTY_STRING = "";

    boolean isNull();

    default String getValueOrEmptyStringIfNull(String value) {
        return (value == null) ? EMPTY_STRING : value;

//        Optional<String> optional = Optional.ofNullable(value);   //не работает в старых api
//        return optional.orElse("");
    }


}
