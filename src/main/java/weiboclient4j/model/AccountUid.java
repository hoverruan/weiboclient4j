package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUid {
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
