package weiboclient4j.params;

import static weiboclient4j.utils.StringUtils.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class TagName extends StringParam {
    public TagName(String value) {
        super(value);
    }

    protected String paramKey() {
        return "tag";
    }

    public static ParameterAction tagsParam(final Collection<TagName> tags) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (tags != null && tags.size() > 0) {
                    List<String> tagList = new ArrayList<String>(tags.size());
                    for (TagName tag : tags) {
                        if (tag.isValid()) {
                            tagList.add(tag.getValue());
                        }
                    }
                    params.add("tags", join(tagList, ","));
                }
            }
        };
    }
}
