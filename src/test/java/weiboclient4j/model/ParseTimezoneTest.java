package weiboclient4j.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
@SuppressWarnings("unchecked")
public class ParseTimezoneTest extends AbstractParseJsonTest {
    @Test
    public void testParseTimezone() throws Exception {
        String content = readResource("/weiboclient4j/model/timezone.json");

        Map<String, String> result = JsonUtils.parseJsonObject(content, HashMap.class);
        assertNotNull(result);

        assertTrue(result.containsKey("001"));
        assertEquals("Zone1", result.get("001"));
        assertEquals("Other", result.get("999"));
    }
}
