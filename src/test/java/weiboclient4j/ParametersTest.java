package weiboclient4j;

import org.junit.Test;

import static org.junit.Assert.*;
import weiboclient4j.params.Parameters;

/**
 * @author Hover Ruan
 */
public class ParametersTest {
    @Test
    public void testBuildParameters() throws Exception {
        Parameters parameters = Parameters.create();

        assertEquals(0, parameters.buildParameters().size());

        parameters.add("key", "value");
        assertEquals("value", parameters.buildParameters().get("key"));
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

        assertEquals("1", parameters.buildParameters().get("key"));
    }

    @Test
    public void testAddLongArrayTwoItem() throws Exception {
        Parameters parameters = Parameters.create();
        long[] ids = new long[]{1, 2};
        parameters.add("key", ids);

        assertEquals("1,2", parameters.buildParameters().get("key"));
    }
}
