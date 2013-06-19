package weiboclient4j.params;

import weiboclient4j.StatusService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum FilterByAuthor implements
        StatusService.GetRepostTimelineParam,
        StatusService.GetRepostTimelineIdsParam,
        StatusService.GetMentionsParam,
        StatusService.GetMentionsIdsParam {

    All(0), Friends(1), Stranger(2);

    private static Map<Integer, FilterByAuthor> map = new HashMap<Integer, FilterByAuthor>();

    static {
        for (FilterByAuthor filterByAuthor : FilterByAuthor.values()) {
            map.put(filterByAuthor.getValue(), filterByAuthor);
        }
    }

    private int value;

    FilterByAuthor(int value) {
        this.value = value;
    }

    public static FilterByAuthor fromValue(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("filter_by_author", getValue());
        }
    }
}
