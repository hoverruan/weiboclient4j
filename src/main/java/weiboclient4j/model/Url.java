package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Url {
    public static final TypeReference<List<Url>> TYPE_URL_LIST = new TypeReference<List<Url>>() {
    };

    private int type;
    private String urlShort;
    private String urlLong;
    private boolean result;
    private int clicks;
    private int shareCounts;
    private int commentCounts;
    private List<UrlReferer> referers;
    private List<UrlLocation> locations;
    private List<Status> shareStatuses;
    private List<Comment> shareComments;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getShareCounts() {
        return shareCounts;
    }

    public void setShareCounts(int shareCounts) {
        this.shareCounts = shareCounts;
    }

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }

    public List<UrlReferer> getReferers() {
        return referers;
    }

    public void setReferers(List<UrlReferer> referers) {
        this.referers = referers;
    }

    public List<UrlLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<UrlLocation> locations) {
        this.locations = locations;
    }

    public List<Status> getShareStatuses() {
        return shareStatuses;
    }

    public void setShareStatuses(List<Status> shareStatuses) {
        this.shareStatuses = shareStatuses;
    }

    public List<Comment> getShareComments() {
        return shareComments;
    }

    public void setShareComments(List<Comment> shareComments) {
        this.shareComments = shareComments;
    }
}
