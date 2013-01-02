package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlLocation {
    private int clicks;
    private int province;
    private String location;

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
