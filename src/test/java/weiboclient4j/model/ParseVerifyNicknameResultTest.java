package weiboclient4j.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseVerifyNicknameResultTest extends AbstractParseJsonTest {
    @Test
    public void testParseVerifyNicknameResult() throws Exception {
        String content = readResource("/weiboclient4j/model/verify_nickname_result.json");

        VerifyNicknameResult response = JsonUtils.readValue(content, VerifyNicknameResult.class);
        assertNotNull(response);
        assertTrue(response.isLegal());
        assertTrue(response.getSuggestNickname().contains("nick001"));
    }
}
