package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusIdList {
    private List<Long> statuses;
    private long totalNumber;

    public List<Long> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Long> statuses) {
        this.statuses = statuses;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }
}
