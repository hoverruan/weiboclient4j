package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePoiResponse {
    private int status;
    private String poiid;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPoiid() {
        return poiid;
    }

    public void setPoiid(String poiid) {
        this.poiid = poiid;
    }
}
