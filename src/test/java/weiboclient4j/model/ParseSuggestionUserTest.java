package weiboclient4j.model;

import org.junit.Test;
import weiboclient4j.AbstractParseJsonTest;
import weiboclient4j.utils.JsonUtils;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 * @author Hover Ruan
 */
public class ParseSuggestionUserTest extends AbstractParseJsonTest {

    @Test
    public void testParseSuggestionUser() throws Exception {
        String content = readResource("/weiboclient4j/model/suggestions_users_may_interested.json");

        List<SuggestionUser> suggestionUserList = JsonUtils.readValue(content, SuggestionUser.TYPE_SUGGESTION_USER_LIST);

        assertThat(suggestionUserList.size(), is(10));

        SuggestionUser user1 = suggestionUserList.get(0);
        assertThat(user1.getUid(), is(1581362064L));

        SuggestionUser.Reason.Item item1 = user1.getReason().getF();
        assertThat(item1.getN(), is(2));
        assertThat(item1.getUid(), hasItem(1919667517L));
        assertThat(item1.getUid(), hasItem(1710369261L));
    }
}
