package weiboclient4j.model;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseAccountUidTest extends AbstractParseJsonTest {
    @Test
    public void testParseAccountUidResponseTest() throws Exception {
        String content = readResource("/weiboclient4j/model/account_uid.json");
        AccountUid response = JsonUtils.readValue(content, AccountUid.class);

        assertEquals("Account uid", 3456676543L, response.getUid());
    }
}
