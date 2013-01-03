package weiboclient4j.model;

import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseEmotionTest extends AbstractParseJsonTest {
    @Test
    public void testParseEmotions() throws Exception {
        String content = readResource("/weiboclient4j/model/emotions.json");

        parseJsonObject(content, Emotion.TYPE_EMOTION_LIST);
    }
}
