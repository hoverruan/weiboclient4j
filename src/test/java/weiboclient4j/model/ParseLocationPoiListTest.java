package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

import java.util.List;

public class ParseLocationPoiListTest extends AbstractParseJsonTest {
    @Test
    public void testParseLocationPoiList() throws Exception {
        String content = readResource("/weiboclient4j/model/location_poi_list.json");
        LocationPoiList locationPoiList = JsonUtils.readValue(content, LocationPoiList.class);

        assertThat(locationPoiList, is(notNullValue()));
        assertThat(locationPoiList.getTotalNumber(), is(equalTo(325L)));

        List<LocationPoi> pois = locationPoiList.getPois();
        assertThat(pois, is(notNullValue()));
        assertThat(pois.size(), is(equalTo(10)));

        LocationPoi poi = pois.get(0);
        assertThat(poi.getLongitude().toString(), is(equalTo("116.31067")));
        assertThat(poi.getLatitude().toString(), is(equalTo("39.98445")));
    }
}
