package weiboclient4j.oauth2;

import org.scribe.model.Token;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2AccessToken extends Token {
    private long expiresIn;
    private long remindIn;

    public SinaWeibo2AccessToken(String token, String secret) {
        super(token, secret);
    }

    public SinaWeibo2AccessToken(SinaWeibo2AccessTokenResponse response) {
        super(response.getAccessToken(), "");

        expiresIn = response.getExpiresIn();
        remindIn = response.getRemindIn();
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public long getRemindIn() {
        return remindIn;
    }
}
