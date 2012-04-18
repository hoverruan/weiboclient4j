package weiboclient4j;

import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseEmotionTest extends AbstractParseJsonTest {
    @Test
    public void testParseEmotions() throws Exception {
        String content = readResource("/weiboclient4j/emotions.json");

        parseJsonObject(content, WeiboClient.TYPE_EMOTION_LIST);
    }
}
