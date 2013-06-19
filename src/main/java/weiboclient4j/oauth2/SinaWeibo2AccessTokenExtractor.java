package weiboclient4j.oauth2;

import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.Token;
import weiboclient4j.WeiboClientException;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hover Ruan
 */
public class SinaWeibo2AccessTokenExtractor implements AccessTokenExtractor {
    private static Logger log = Logger.getLogger("weiboclient2/access_token_extractor");

    public Token extract(String response) {
        try {
            SinaWeibo2AccessTokenResponse tokenResponse =
                    parseJsonObject(response, SinaWeibo2AccessTokenResponse.class);
            return new SinaWeibo2AccessToken(tokenResponse);
        } catch (WeiboClientException e) {
            log.log(Level.WARNING, "Failed to parse access token response: " + e.getMessage(), e);
            return null;
        }
    }
}
