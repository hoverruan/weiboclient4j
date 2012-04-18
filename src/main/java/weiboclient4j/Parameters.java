package weiboclient4j;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public class Parameters {
    private Map<String, String> params = new HashMap<String, String>();

    private Parameters() {

    }

    public static Parameters create() {
        return new Parameters();
    }

    public Parameters add(String key, String value) {
        params.put(key, value);

        return this;
    }

    Parameters add(Paging paging) {
        params.putAll(paging.buildParameters());

        return this;
    }

    public Parameters add(String key, long value) {
        return add(key, String.valueOf(value));
    }

    public Parameters add(String key, boolean value) {
        return add(key, value ? 1 : 0);
    }

    public Parameters add(String key, long[] valueArray) {
        if (valueArray != null && valueArray.length > 0) {
            StringBuilder result = new StringBuilder(String.valueOf(valueArray[0]));
            for (int i = 1; i < valueArray.length; i++) {
                long value = valueArray[i];
                result.append(',').append(value);
            }

            add(key, result.toString());
        }

        return this;
    }

    public boolean isEmpty() {
        return params.size() == 0;
    }

    public boolean isNotEmpty() {
        return params.size() > 0;
    }

    public Map<String, String> buildParameters() {
        return params;
    }

    public void appendTo(OAuthRequest request) {
        if (request.getVerb() == Verb.GET) {
            for (String key : params.keySet()) {
                request.addQuerystringParameter(key, params.get(key));
            }
        } else {
            for (String key : params.keySet()) {
                request.addBodyParameter(key, params.get(key));
            }
        }
    }
}
