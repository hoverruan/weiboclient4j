package weiboclient4j;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import weiboclient4j.oauth2.SinaWeibo2Api;
import weiboclient4j.utils.SinaJsonNamingStrategy;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * @author Hover Ruan
 */
public class WeiboClient2 {
    private static final Logger log = Logger.getLogger("weibo_client2");

    private OAuthService service;

    public WeiboClient2(String apiKey, String apiSecret, String callback) {
        SinaWeibo2Api api = new SinaWeibo2Api();

        service = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .provider(api)
                .callback(callback)
                .build();
    }
}
