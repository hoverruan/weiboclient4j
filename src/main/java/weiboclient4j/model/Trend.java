package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trend {
    private long trendId;
    private String hotword;
    private long num;

    public long getTrendId() {
        return trendId;
    }

    public void setTrendId(long trendId) {
        this.trendId = trendId;
    }

    public String getHotword() {
        return hotword;
    }

    public void setHotword(String hotword) {
        this.hotword = hotword;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
