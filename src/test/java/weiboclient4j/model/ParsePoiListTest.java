package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;
import weiboclient4j.utils.SimpleDateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class ParsePoiListTest extends AbstractParseJsonTest {
    @Test
    public void testParsePoiList() throws Exception {
        String content = readResource("/weiboclient4j/model/poi_list.json");

        PoiList poiList = JsonUtils.readValue(content, PoiList.class);

        assertThat(poiList, is(notNullValue()));
        assertThat(poiList.getTotalNumber(), is(65));

        List<Poi> pois = poiList.getPois();
        assertThat(pois, is(notNullValue()));
        assertThat(pois.size(), is(20));

        Poi poi = pois.get(0);
        Date checkinTime = SimpleDateDeserializer.DATE_FORMAT.parse("2012-11-19 00:01:13");
        assertThat(poi.getCheckinTime(), is(checkinTime));
    }
}
