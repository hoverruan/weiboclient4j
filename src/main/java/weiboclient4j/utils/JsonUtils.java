package weiboclient4j.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.scribe.model.Response;
import weiboclient4j.WeiboClientException;
import weiboclient4j.WeiboError;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * @author Hover Ruan
 */
public final class JsonUtils {
    private static Logger log = Logger.getLogger("weiboclient2/json");

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setPropertyNamingStrategy(new SinaJsonNamingStrategy());

        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(format);
    }

    private JsonUtils() {
    }

    public static <T> T readValue(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, valueType);
    }

    public static <T> T readValue(String content, TypeReference valueTypeRef) throws IOException {
        return mapper.readValue(content, valueTypeRef);
    }

    public static <T> T parseJsonObject(Response response, Class<T> clazz) throws WeiboClientException {
        if (response.isSuccessful()) {
            return parseJsonObject(response.getCode(), response.getBody(), clazz);
        } else {
            throw createException(response);
        }
    }

    public static <T> List<T> parseJsonObject(Response response, TypeReference<List<T>> type)
            throws WeiboClientException {
        if (response.isSuccessful()) {
            return parseJsonObject(response.getCode(), response.getBody(), type);
        } else {
            throw createException(response);
        }
    }

    public static <T> List<T> parseJsonObject(String content, TypeReference<List<T>> type) throws WeiboClientException {
        try {
            return readValue(content, type);
        } catch (IOException e) {
            handleWeiboError(0, content, e);
            return null;
        }
    }

    private static <T> List<T> parseJsonObject(int code, String body, TypeReference<List<T>> type)
            throws WeiboClientException {
        try {
            return readValue(body, type);
        } catch (IOException e) {
            handleWeiboError(code, body, e);
            return null;
        }
    }

    public static <T> T parseJsonObject(String content, Class<T> clazz) throws WeiboClientException {
        try {
            return readValue(content, clazz);
        } catch (IOException e) {
            handleWeiboError(0, content, e);
            return null;
        }
    }

    private static <T> T parseJsonObject(int code, String content, Class<T> clazz) throws WeiboClientException {
        try {
            return readValue(content, clazz);
        } catch (IOException e) {
            handleWeiboError(code, content, e);
            return null;
        }
    }

    private static WeiboClientException createException(Response response) {
        String body = response.getBody();
        int code = response.getCode();

        if (isNotBlank(body)) {
            try {
                WeiboError error = readValue(body, WeiboError.class);
                return new WeiboClientException(code, body, error);
            } catch (IOException e) {
                return new WeiboClientException(code, body, e);
            }
        } else {
            return new WeiboClientException(code);
        }
    }

    private static void handleWeiboError(int code, String content, IOException e) throws WeiboClientException {
        try {
            WeiboError error = readValue(content, WeiboError.class);
            throw new WeiboClientException(code, content, error);
        } catch (IOException ioe) {
            throw new WeiboClientException(code, content, e);
        }
    }

    public static String writeObjectAsString(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
