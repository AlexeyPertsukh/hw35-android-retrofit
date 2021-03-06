package jsonplaceholder_class;

public class GeoNull extends Geo implements INull {
    private static GeoNull geoNull;

    private GeoNull() {
    }

    protected static GeoNull getInstance() {
        if(geoNull == null) {
            geoNull = new GeoNull();
        }
        return geoNull;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    public String getGeoString() {
        return EMPTY_STRING;
    }
}
