package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

public class ParseTokenInfoTest extends AbstractParseJsonTest {
    @Test
    public void testParseTokenInfo() throws Exception {
        String content = readResource("/weiboclient4j/model/token_info.json");

        TokenInfo tokenInfo = JsonUtils.parseJsonObject(content, TokenInfo.class);

        assertThat(tokenInfo, is(notNullValue()));
        assertThat(tokenInfo.getUid(), is(equalTo(1073880650L)));
        assertThat(tokenInfo.getAppkey(), is(equalTo("1352222456")));
        assertThat(tokenInfo.getScope(), is(nullValue()));
        assertThat(tokenInfo.getCreateAt(), is(equalTo(1352267591L)));
        assertThat(tokenInfo.getExpireIn(), is(equalTo(157679471)));
    }
}
