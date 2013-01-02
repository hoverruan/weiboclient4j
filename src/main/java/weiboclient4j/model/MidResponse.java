package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MidResponse {
    private String mid;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
