package weiboclient4j.model;

import org.codehaus.jackson.type.TypeReference;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public class ParseCountryTest extends AbstractParseJsonTest {
    @Test
    public void testParseCountry() throws Exception {
        String content = readResource("/weiboclient4j/model/country.json");
        List<Map<String, String>> result = JsonUtils.parseJsonObject(content, new TypeReference<List<Map<String, String>>>() {
        });

        assertNotNull(result);
        assertEquals(2, result.size());

        assertTrue(result.get(0).containsKey("001"));
        assertEquals("C1", result.get(0).get("001"));

        assertTrue(result.get(1).containsKey("002"));
        assertEquals("C2", result.get(1).get("002"));
    }
}
