package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class Mid extends StringParam {

    public static final String MID = "mid";

    public Mid(String value) {
        super(value);
    }

    protected String paramKey() {
        return MID;
    }

    public static ParameterAction midParam(final Collection<Mid> midList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (midList != null && midList.size() > 0) {
                    List<String> midStringList = new ArrayList<String>(midList.size());
                    for (Mid mid : midList) {
                        midStringList.add(mid.getValue());
                    }
                    params.add(MID, join(midStringList, ","));
                }
            }
        };
    }
}
