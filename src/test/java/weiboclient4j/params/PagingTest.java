package weiboclient4j.params;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class PagingTest {
    @Test
    public void testHasValue() throws Exception {
        Paging paging = Paging.create();

        assertFalse(paging.hasValue());

        paging.count(20);

        assertTrue(paging.hasValue());
    }
}
