package weiboclient4j.params;

import java.util.Collection;

/**
 * @author Hover Ruan
 */
public class UrlShort extends StringParam {

    public static final String URL_SHORT = "url_short";

    public UrlShort(String value) {
        super(value);
    }

    protected String paramKey() {
        return URL_SHORT;
    }

    public static ParameterAction urlShortParam(final Collection<UrlShort> urlList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (urlList != null && urlList.size() > 0) {
                    for (UrlShort urlShort : urlList) {
                        if (urlShort.isValid()) {
                            params.add(URL_SHORT, urlShort.getValue());
                        }
                    }
                }
            }
        };
    }
}
