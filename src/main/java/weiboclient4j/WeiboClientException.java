package weiboclient4j;

/**
 * @author Hover Ruan
 */
public class WeiboClientException extends Exception {
    public WeiboClientException() {
    }

    public WeiboClientException(String message) {
        super(message);
    }

    public WeiboClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeiboClientException(Throwable cause) {
        super(cause);
    }
}
