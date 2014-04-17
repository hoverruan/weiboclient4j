package weiboclient4j.model;

import java.util.List;

public class LocationPoiList {
    private List<LocationPoi> pois;
    private long totalNumber;

    public List<LocationPoi> getPois() {
        return pois;
    }

    public void setPois(List<LocationPoi> pois) {
        this.pois = pois;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }
}
