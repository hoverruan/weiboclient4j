package weiboclient4j.oauth2;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2AccessTokenResponse {
    private String accessToken;
    private long expiresIn;
    private long remindIn;
    private long uid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getRemindIn() {
        return remindIn;
    }

    public void setRemindIn(long remindIn) {
        this.remindIn = remindIn;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
