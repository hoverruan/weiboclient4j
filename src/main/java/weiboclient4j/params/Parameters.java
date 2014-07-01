package weiboclient4j.params;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public final class Parameters {
    private List<Parameter> parameterList = new ArrayList<Parameter>();

    private Parameters() {

    }

    public static Parameters create() {
        return new Parameters();
    }

    public Parameters add(String key, String value) {
        parameterList.add(new Parameter(key, value));

        return this;
    }

    public Parameters add(Paging paging) {
        for (Map.Entry<String, String> entry : paging.buildParameters().entrySet()) {
            add(entry.getKey(), entry.getValue());
        }

        return this;
    }

    public Parameters add(String key, long value) {
        return add(key, String.valueOf(value));
    }

    public Parameters add(String key, boolean value) {
        return add(key, String.valueOf(value));
    }

    public Parameters add(String key, float value) {
        return add(key, String.valueOf(value));
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
        return parameterList.size() == 0;
    }

    public boolean isNotEmpty() {
        return parameterList.size() > 0;
    }

    public String get(String key) {
        for (Parameter parameter : parameterList) {
            if (key.equals(parameter.getKey())) {
                return parameter.getValue();
            }
        }

        return null;
    }

    public int size() {
        return parameterList.size();
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void appendTo(OAuthRequest request) {
        if (request.getVerb() == Verb.GET) {
            for (Parameter p : parameterList) {
                request.addQuerystringParameter(p.getKey(), p.getValue());
            }
        } else {
            for (Parameter p : parameterList) {
                request.addBodyParameter(p.getKey(), p.getValue());
            }
        }
    }

    public Parameters addAll(ParameterAction[] actions) {
        for (ParameterAction action : actions) {
            if (action != null) {
                action.addParameter(this);
            }
        }

        return this;
    }

    public static final class Parameter {
        private String key;
        private String value;

        private Parameter(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
