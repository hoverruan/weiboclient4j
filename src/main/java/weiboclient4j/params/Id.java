package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class Id extends LongParam {

    public static final String ID = "id";
    public static final String IDS = "ids";

    public Id(long value) {
        super(value);
    }

    public Id(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return ID;
    }

    public static ParameterAction idParam(final Collection<Id> idList) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (idList != null && idList.size() > 0) {
                    List<String> idStringList = new ArrayList<String>(idList.size());
                    for (Id id : idList) {
                        idStringList.add(String.valueOf(id.getValue()));
                    }
                    params.add(ID, join(idStringList, ","));
                }
            }
        };
    }

    public static ParameterAction idsParam(final Collection<Id> ids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (ids != null && ids.size() > 0) {
                    List<String> idStringList = new ArrayList<String>(ids.size());
                    for (Id id : ids) {
                        idStringList.add(String.valueOf(id.getValue()));
                    }
                    String idsString = join(idStringList, ",");
                    params.add(IDS, idsString);
                }
            }
        };
    }
}
