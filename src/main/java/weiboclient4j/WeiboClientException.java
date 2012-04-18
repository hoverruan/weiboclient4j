package weiboclient4j;

import java.io.IOException;

/**
 * @author Hover Ruan
 */
public class WeiboClientException extends Exception {
    private int responseCode;
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

    public WeiboClientException(int code, WeiboError error) {
        this(error);

        responseCode = code;
    }

    public WeiboClientException(int code, Throwable cause) {
        this(cause);

        responseCode = code;
    }

    public WeiboClientException(int code) {
        responseCode = code;
    }

    public boolean isWeiboError() {
        return error != null;
    }

    public WeiboError getWeiboError() {
        return error;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
