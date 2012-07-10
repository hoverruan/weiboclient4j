package weiboclient4j.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseTrendStatusTest extends AbstractParseJsonTest {

    @Test
    public void testParseTrendStatus() throws Exception {
        String content = readResource("/weiboclient4j/model/trend_status.json");

        TrendStatus status = JsonUtils.parseJsonObject(content, TrendStatus.class);
        assertNotNull(status);
        assertTrue(status.isFollow());
    }
}
