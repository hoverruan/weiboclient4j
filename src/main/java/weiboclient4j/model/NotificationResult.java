package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationResult {
    private Notification notification;
    private List<Long> failedUid;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<Long> getFailedUid() {
        return failedUid;
    }

    public void setFailedUid(List<Long> failedUid) {
        this.failedUid = failedUid;
    }
}
