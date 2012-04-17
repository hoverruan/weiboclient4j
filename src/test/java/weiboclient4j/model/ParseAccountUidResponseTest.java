package weiboclient4j.model;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseAccountUidResponseTest extends AbstractParseJsonTest {
    @Test
    public void testParseAccountUidResponseTest() throws Exception {
        String content = readResource("/weiboclient4j/model/account_uid_response.json");
        AccountUidResponse response = JsonUtils.readValue(content, AccountUidResponse.class);

        assertEquals("Account uid", 3456676543L, response.getUid());
    }
}
