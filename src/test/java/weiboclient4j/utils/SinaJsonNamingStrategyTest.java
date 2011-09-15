package weiboclient4j.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Hover Ruan
 */
public class SinaJsonNamingStrategyTest {
    private SinaJsonNamingStrategy strategy = new SinaJsonNamingStrategy();

    @Test
    public void testScreenName() {
        assertEquals("screen_name", strategy.convertName("screenName"));
    }

    @Test
    public void testProfileImageUrl() {
        assertEquals("profile_image_url", strategy.convertName("profileImageUrl"));
    }
}
