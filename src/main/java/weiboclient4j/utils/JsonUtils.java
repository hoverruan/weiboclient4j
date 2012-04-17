package weiboclient4j.utils;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import weiboclient4j.WeiboClientException;
import weiboclient4j.WeiboError;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * @author Hover Ruan
 */
public class JsonUtils {
    private static final Logger log = Logger.getLogger("weiboclient2/json");
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setPropertyNamingStrategy(new SinaJsonNamingStrategy());

        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.getDeserializationConfig().setDateFormat(format);
    }

    private JsonUtils() {
    }

    public static <T> T readValue(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, valueType);
    }

    public static <T> T readValue(String content, TypeReference valueTypeRef) throws IOException {
        return mapper.readValue(content, valueTypeRef);
    }

    public static <T> T parseJsonObject(String content, Class<T> clazz) throws WeiboClientException {
        try {
            return readValue(content, clazz);
        } catch (IOException e) {
            handleWeiboError(content, e);
            return null;
        }
    }

    public static <T> List<T> parseJsonObject(String content, TypeReference<List<T>> type) throws WeiboClientException {
        try {
            return readValue(content, type);
        } catch (IOException e) {
            handleWeiboError(content, e);
            return null;
        }
    }

    private static void handleWeiboError(String content, IOException e) throws WeiboClientException {
        try {
            WeiboError error = JsonUtils.readValue(content, WeiboError.class);
            throw new WeiboClientException(error);
        } catch (IOException ex) {
            log.warning("Parse failed: " + content);
            throw new WeiboClientException(e);
        }
    }
}
