package weiboclient4j.oauth2;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SinaWeibo2AccessTokenResponse {
    private String accessToken;
    private long expiresIn;
    private long remindIn;
    private long uid;
    private boolean realName;

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

    public boolean isRealName() {
        return realName;
    }

    @JsonProperty("isRealName")
    public void setRealName(boolean realName) {
        this.realName = realName;
    }
}
