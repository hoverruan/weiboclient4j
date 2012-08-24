package weiboclient4j.params;

import java.util.Collection;

/**
 * @author Hover Ruan
 */
public class UrlLong extends StringParam {

    public static final String URL_LONG = "url_long";

    public UrlLong(String value) {
        super(value);
    }

    protected String paramKey() {
        return URL_LONG;
    }

    public static ParameterAction urlLongParam(final Collection<UrlLong> urlList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (urlList != null && urlList.size() > 0) {
                    for (UrlLong urlLong : urlList) {
                        if (urlLong.isValid()) {
                            params.add(URL_LONG, urlLong.getValue());
                        }
                    }
                }
            }
        };
    }
}
