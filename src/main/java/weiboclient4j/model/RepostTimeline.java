package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepostTimeline {
    public static final RepostTimeline EMPTY = new RepostTimeline() {
        {
            setReposts(new ArrayList<Status>());
        }
    };

    @JsonProperty("hasvisible")
    private boolean visible;
    private long previousCursor;
    private long nextCursor;
    private int totalNumber;
    private List<Status> reposts;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<Status> getReposts() {
        return reposts;
    }

    public void setReposts(List<Status> reposts) {
        this.reposts = reposts;
    }
}
