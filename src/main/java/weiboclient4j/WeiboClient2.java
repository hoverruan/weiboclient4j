package weiboclient4j;

/**
 * @author Hover Ruan
 * @deprecated Please use {@link WeiboClient}
 */
public class WeiboClient2 extends WeiboClient {
    /**
     * Create api client v2.
     *
     * @param clientId     Client ID, or Api Key
     * @param clientSecret Client Secret, or Api Secret
     */
    public WeiboClient2(String clientId, String clientSecret) {
        super(clientId, clientSecret);
    }
}
