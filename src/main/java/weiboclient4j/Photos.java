package weiboclient4j;

/**
 * @author Hover Ruan
 */
public class Photos {
    private String relatedPhotos;
    private int feedType;
    private long inFeedToId;
    private long uid;
    private String url;

    public String getRelatedPhotos() {
        return relatedPhotos;
    }

    public void setRelatedPhotos(String relatedPhotos) {
        this.relatedPhotos = relatedPhotos;
    }

    public int getFeedType() {
        return feedType;
    }

    public void setFeedType(int feedType) {
        this.feedType = feedType;
    }

    public long getInFeedToId() {
        return inFeedToId;
    }

    public void setInFeedToId(long inFeedToId) {
        this.inFeedToId = inFeedToId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
