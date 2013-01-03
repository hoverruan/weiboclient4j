package weiboclient4j.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

/**
 * @author Hover Ruan
 */
public class ParseNotificationResultTest extends AbstractParseJsonTest {
    @Test
    public void testParseNotificationResult() throws Exception {
        String content = readResource("/weiboclient4j/model/notification_result.json");
        NotificationResult result = JsonUtils.readValue(content, NotificationResult.class);

        assertNotNull(result);

        assertEquals(12035, result.getNotification().getNotificationId());
        assertEquals("4Gcqmc", result.getNotification().getSenderApp().getAppkey62());

        assertTrue(result.getFailedUid().contains(21485798475L));
        assertTrue(result.getFailedUid().contains(21485798476L));
    }
}
