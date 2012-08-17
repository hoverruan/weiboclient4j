package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {
    private long notificationId;
    private String title;
    private String content;
    private Date createdAt;
    private SenderApp senderApp;
    private long senderUid;

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public SenderApp getSenderApp() {
        return senderApp;
    }

    public void setSenderApp(SenderApp senderApp) {
        this.senderApp = senderApp;
    }

    public long getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(long senderUid) {
        this.senderUid = senderUid;
    }
}
