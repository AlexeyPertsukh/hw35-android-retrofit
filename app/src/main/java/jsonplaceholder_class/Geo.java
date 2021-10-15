package jsonplaceholder_class;

import android.annotation.SuppressLint;

import java.io.Serializable;

public class Geo implements Serializable, INull {
    private double lat;
    private double lng;

    protected Geo() {
    }

    @SuppressWarnings("unused")
    public Geo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @SuppressWarnings("unused")
    public static Geo getInstanceNull() {
        return GeoNull.getInstance();
    }

    @SuppressLint("DefaultLocale")
    public String getGeoString() {
        return String.format("%f : %f", lat, lng);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
