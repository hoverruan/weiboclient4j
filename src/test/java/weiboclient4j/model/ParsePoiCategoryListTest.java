package weiboclient4j.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import weiboclient4j.utils.JsonUtils;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class ParsePoiCategoryListTest extends AbstractParseJsonTest {
    @Test
    public void parsePoiCategoryList() throws Exception {
        String content = readResource("/weiboclient4j/model/poi_category_list.json");
        List<PoiCategory> categories = JsonUtils.readValue(content, PoiCategory.POI_CATEGORY_LIST);

        assertThat(categories, is(notNullValue()));
        assertThat(categories.size(), is(9));

        PoiCategory category = categories.get(4);
        assertThat(category.getId(), is("115"));
    }
}
