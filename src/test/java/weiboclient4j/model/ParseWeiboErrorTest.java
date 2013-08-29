package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.WeiboError;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseWeiboErrorTest extends AbstractParseJsonTest {
    @Test
    public void testParseWeiboError() throws Exception {
        String content = readResource("/weiboclient4j/model/error.json");

        WeiboError error = JsonUtils.readValue(content, WeiboError.class);
        assertThat(error, is(notNullValue()));
        assertThat(error.getErrorCodeAsInt(), is(20502));
    }
}
