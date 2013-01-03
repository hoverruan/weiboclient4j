package weiboclient4j.params;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Hover Ruan
 */
public class ParametersTest {
    @Test
    public void testBuildParameters() throws Exception {
        Parameters parameters = Parameters.create();

        assertEquals(0, parameters.size());

        parameters.add("key", "value");
        assertEquals("value", parameters.get("key"));
    }

    @Test
    public void testIsEmpty() throws Exception {
        Parameters parameters = Parameters.create();
        assertTrue(parameters.isEmpty());
        assertFalse(parameters.isNotEmpty());

        parameters.add("key", "value");
        assertFalse(parameters.isEmpty());
        assertTrue(parameters.isNotEmpty());
    }

    @Test
    public void testAddLongArrayOneItem() throws Exception {
        Parameters parameters = Parameters.create();
        long[] ids = new long[]{1};
        parameters.add("key", ids);

        assertEquals("1", parameters.get("key"));
    }

    @Test
    public void testAddLongArrayTwoItem() throws Exception {
        Parameters parameters = Parameters.create();
        long[] ids = new long[]{1, 2};
        parameters.add("key", ids);

        assertEquals("1,2", parameters.get("key"));
    }
}
