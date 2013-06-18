package weiboclient4j.params;

public class Coordinate {
    private Longitude longitude;

    private Latitude latitude;

    public Coordinate(Longitude longitude, Latitude latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return String.format("%f,%f", longitude.getValue(), latitude.getValue());
    }
}
