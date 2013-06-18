package weiboclient4j.params;

import weiboclient4j.LocationService;
import static weiboclient4j.utils.StringUtils.join;

public class Names extends StringParam implements
        LocationService.GetMapImageParam {

    public Names(String... value) {
        super(join(value, ","));
    }

    protected String paramKey() {
        return "names";
    }
}
