package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class Uid extends LongParam {
    public static final Uid EMPTY = new Uid(0);

    public Uid(long value) {
        super(value);
    }

    public Uid(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return "uid";
    }

    public static ParameterAction uidsParam(final Collection<Uid> uids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (uids != null && uids.size() > 0) {
                    List<String> idList = new ArrayList<String>();
                    for (Uid uid : uids) {
                        idList.add(String.valueOf(uid.getValue()));
                    }
                    params.add("uids", join(idList, ","));
                }
            }
        };
    }
}
