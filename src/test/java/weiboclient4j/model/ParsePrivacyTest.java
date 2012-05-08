package weiboclient4j.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParsePrivacyTest extends AbstractParseJsonTest {
    @Test
    public void testParsePrivacy() throws Exception {
        String content = readResource("/weiboclient4j/model/privacy.json");

        Privacy privacy = JsonUtils.readValue(content, Privacy.class);
        assertTrue(privacy.isBadge());
        assertFalse(privacy.isComment());
        assertTrue(privacy.isGeo());
        assertTrue(privacy.isMessage());
        assertTrue(privacy.isMobile());
        assertFalse(privacy.isRealname());
    }
}
