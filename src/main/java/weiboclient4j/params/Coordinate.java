package weiboclient4j.params;

public class Coordinate extends StringParam {
    private Longitude longitude;

    private Latitude latitude;

    public Coordinate(Longitude longitude, Latitude latitude) {
        super(formatCoordinate(longitude, latitude));

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
        return formatCoordinate(longitude, latitude);
    }

    @Override
    protected String paramKey() {
        return "coordinate";
    }

    private static String formatCoordinate(Longitude longitudeParam, Latitude latitudeParam) {
        return String.format("%f,%f", longitudeParam.getValue(), latitudeParam.getValue());
    }
}
