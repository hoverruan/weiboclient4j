package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendStatus {

    private long trendId;

    @JsonProperty("is_follow")
    private boolean follow;

    public long getTrendId() {
        return trendId;
    }

    public void setTrendId(long trendId) {
        this.trendId = trendId;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
