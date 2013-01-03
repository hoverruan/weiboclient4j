package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static weiboclient4j.utils.JsonUtils.readValue;

/**
 * @author Hover Ruan
 */
public class ParsePoiTest extends AbstractParseJsonTest {
    @Test
    public void parsePoi() throws Exception {
        String content = readResource("/weiboclient4j/model/poi.json");
        Poi poi = readValue(content, Poi.class);

        assertThat(poi, is(notNullValue()));
        assertThat(poi.getPoiid(), is("B2094654D16CABFE419E"));
        assertThat(poi.getLon(), is(116.30987));
        assertThat(poi.getLat(), is(39.98437));
        assertThat(poi.getCategory(), is("46"));
        assertThat(poi.getCity(), is("0010"));
        assertThat(poi.getPhone(), is("010-82607123"));
        assertThat(poi.getPostcode(), is("100000"));
        assertThat(poi.getWeiboId(), is("2513895201"));
        assertThat(poi.getIcon(), is("http://u1.sinaimg.cn/upload/lbs/poi/icon/88/46.png"));
        assertThat(poi.getRid(), is("46"));
        assertThat(poi.getCategorys(), is("44 46"));
        assertThat(poi.getCheckinNum(), is(3114));
        assertThat(poi.getCheckinUserNum(), is(1315));
        assertThat(poi.getTipNum(), is(3080));
        assertThat(poi.getPhotoNum(), is(1119));
        assertThat(poi.getTodoNum(), is(106));
    }
}
