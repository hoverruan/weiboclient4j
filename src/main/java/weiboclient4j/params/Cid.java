package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class Cid extends LongParam {
    public Cid(long value) {
        super(value);
    }

    public Cid(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return "cid";
    }

    public static ParameterAction cidsParam(final Collection<Cid> cids) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (cids != null && cids.size() > 0) {
                    List<String> idList = new ArrayList<String>();
                    for (Cid cid : cids) {
                        if (cid.isValid()) {
                            idList.add(String.valueOf(cid.getValue()));
                        }
                    }
                    String idListString = join(idList, ",");
                    params.add("cids", idListString);
                }
            }
        };
    }
}
