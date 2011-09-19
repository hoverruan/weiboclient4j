package weiboclient4j;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class GlobalTrendList {
    private String time;
    private List<GlobalTrend> trends;
    private long asOf;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<GlobalTrend> getTrends() {
        return trends;
    }

    public void setTrends(List<GlobalTrend> trends) {
        this.trends = trends;
    }

    public long getAsOf() {
        return asOf;
    }

    public void setAsOf(long asOf) {
        this.asOf = asOf;
    }
}
