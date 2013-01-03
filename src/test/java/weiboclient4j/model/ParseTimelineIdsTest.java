package weiboclient4j.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseTimelineIdsTest extends AbstractParseJsonTest {
    @Test
    public void testParseTimelineIds() throws Exception {
        String content = readResource("/weiboclient4j/model/timeline_ids.json");
        TimelineIds ids = JsonUtils.readValue(content, TimelineIds.class);
        assertEquals("Total number", 16, ids.getTotalNumber());
        assertTrue("Contains id", ids.getStatuses().contains(3382905252160340L));
    }
}
