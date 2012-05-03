package weiboclient4j.oauth2;

import org.scribe.model.Token;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2AccessToken extends Token {
    private long expiresIn;
    private long remindIn;
    private long uid;

    public SinaWeibo2AccessToken(String token, String secret) {
        super(token, secret);
    }

    public SinaWeibo2AccessToken(String token) {
        this(token, "");
    }

    public SinaWeibo2AccessToken(SinaWeibo2AccessTokenResponse response) {
        this(response.getAccessToken(), "");

        expiresIn = response.getExpiresIn();
        remindIn = response.getRemindIn();
        uid = response.getUid();
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getRemindIn() {
        return remindIn;
    }

    public long getUid() {
        return uid;
    }
}
