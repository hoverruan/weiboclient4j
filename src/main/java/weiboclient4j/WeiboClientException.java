package weiboclient4j;

/**
 * @author Hover Ruan
 */
public class WeiboClientException extends Exception {
    private WeiboError error;

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

    public WeiboClientException(WeiboError error) {
        this(error.getError());

        this.error = error;
    }

    public boolean isWeiboError() {
        return error != null;
    }

    public WeiboError getWeiboError() {
        return error;
    }
}
