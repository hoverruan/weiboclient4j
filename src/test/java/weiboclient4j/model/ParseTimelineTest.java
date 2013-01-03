package weiboclient4j.model;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseTimelineTest extends AbstractParseJsonTest {
    @Test
    public void testParseTimeline() throws Exception {
        String content = readResource("/weiboclient4j/model/timeline.json");

        Timeline timeline = JsonUtils.readValue(content, Timeline.class);
        assertEquals("Total number: ", 20, timeline.getTotalNumber());
    }
}
