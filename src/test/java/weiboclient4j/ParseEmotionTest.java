package weiboclient4j;

import org.junit.Test;
import weiboclient4j.model.Emotion;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;

/**
 * @author Hover Ruan
 */
public class ParseEmotionTest extends AbstractParseJsonTest {
    @Test
    public void testParseEmotions() throws Exception {
        String content = readResource("/weiboclient4j/emotions.json");

        parseJsonObject(content, Emotion.TYPE_EMOTION_LIST);
    }
}
