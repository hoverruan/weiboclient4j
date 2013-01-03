package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.junit.matchers.JUnitMatchers.hasItem;
import weiboclient4j.utils.JsonUtils;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class ParseRawSuggestionUserTest extends AbstractParseJsonTest {

    public static final long FIRST_UID = 1581362064L;

    @Test
    public void testParseSuggestionUser() throws Exception {
        String content = readResource("/weiboclient4j/model/suggestions_users_may_interested.json");

        List<RawSuggestionUser> rawSuggestionUserList = JsonUtils.readValue(content, RawSuggestionUser.TYPE_SUGGESTION_USER_LIST);

        assertThat(rawSuggestionUserList.size(), is(10));

        RawSuggestionUser rawUser0 = rawSuggestionUserList.get(0);
        assertThat(rawUser0.getUid(), is(FIRST_UID));

        RawSuggestionUser.Reason.Item item0 = rawUser0.getReason().getF();
        assertThat("item0.getN()", item0.getN(), is(2));
        assertThat(item0.getUid(), hasItem(1919667517L));
        assertThat(item0.getUid(), hasItem(1710369261L));

        List<SuggestionUser> suggestionUsers = SuggestionUser.convertFromRawSuggestionUserList(rawSuggestionUserList);
        assertThat(suggestionUsers, is(notNullValue()));

        SuggestionUser user0 = suggestionUsers.get(0);
        assertThat(user0.getUid(), is(FIRST_UID));
        assertThat(user0.getReasonList().size(), is(1));

        SuggestionUser.Reason reason0ForUser0 = user0.getReasonList().get(0);
        assertThat(reason0ForUser0.getType(), is(SuggestionUser.ReasonType.F));
        assertThat("reason0ForUser0.getNumOfRelationship()", reason0ForUser0.getNumOfRelationship(), is(2));

        SuggestionUser user6 = suggestionUsers.get(6);
        assertThat(user6.getReasonList().size(), is(2));
    }
}
