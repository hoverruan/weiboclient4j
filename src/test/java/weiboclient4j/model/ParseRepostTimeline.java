package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

public class ParseRepostTimeline extends AbstractParseJsonTest {
    @Test
    public void testParseEmptyRepostTimeline() throws Exception {
        String content = readResource("/weiboclient4j/model/empty_repost_timeline.json");

        RepostTimeline timeline = JsonUtils.readValue(content, RepostTimeline.class);
        assertThat(timeline, is(notNullValue()));
    }
}
