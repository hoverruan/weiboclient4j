package weiboclient4j;

import weiboclient4j.utils.StringUtils;

/**
 * @author Hover Ruan
 */
public class WeiboClientException extends Exception {
    private int responseCode;
    private String responseBody;
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
        this(error.toString());

        this.error = error;
    }

    @Override
    public String getMessage() {
        String sourceMessage = super.getMessage();

        if (StringUtils.isNotBlank(responseBody)) {
            return String.format("<%d: %s> %s", responseCode, responseBody, sourceMessage);
        } else if (responseCode != 0) {
            return String.format("<%d> %s", responseCode, sourceMessage);
        } else {
            return sourceMessage;
        }
    }

    public WeiboClientException(int code) {
        responseCode = code;
    }

    public WeiboClientException(int code, String body, WeiboError error) {
        this(error);

        responseCode = code;
        responseBody = body;
    }

    public WeiboClientException(int code, String body, Throwable cause) {
        this(cause);

        responseCode = code;
        responseBody = body;
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

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
