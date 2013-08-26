package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class TagId extends LongParam {
    public TagId(long value) {
        super(value);
    }

    public TagId(String value) {
        this(Long.parseLong(value));
    }

    protected String paramKey() {
        return "tag_id";
    }

    public static ParameterAction idsParam(final Collection<TagId> tagIds) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (tagIds != null && tagIds.size() > 0) {
                    List<String> idStringList = new ArrayList<String>(tagIds.size());
                    for (TagId tagId : tagIds) {
                        idStringList.add(String.valueOf(tagId.getValue()));
                    }
                    String idsString = join(idStringList, ",");
                    params.add("ids", idsString);
                }
            }
        };
    }
}
