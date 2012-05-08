package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Privacy {
    private boolean badge;
    private boolean comment;
    private boolean geo;
    private boolean message;
    private boolean mobile;
    private boolean realname;

    public boolean isBadge() {
        return badge;
    }

    public void setBadge(boolean badge) {
        this.badge = badge;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isGeo() {
        return geo;
    }

    public void setGeo(boolean geo) {
        this.geo = geo;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public boolean isRealname() {
        return realname;
    }

    public void setRealname(boolean realname) {
        this.realname = realname;
    }
}
