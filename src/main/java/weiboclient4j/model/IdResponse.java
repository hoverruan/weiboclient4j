package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdResponse {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
