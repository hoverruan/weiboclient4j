package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckIn {
    private String poiid;
    private String title;
    private String address;
    private double lon;
    private double lat;

}
