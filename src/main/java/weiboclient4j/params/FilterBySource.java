package weiboclient4j.params;

import weiboclient4j.StatusService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum FilterBySource implements
        StatusService.GetMentionsParam,
        StatusService.GetMentionsIdsParam {

    All(0), FromWeibo(1), FromWeiqun(2);

    private static Map<Integer, FilterBySource> map = new HashMap<Integer, FilterBySource>();

    static {
        for (FilterBySource filterBySource : FilterBySource.values()) {
            map.put(filterBySource.getValue(), filterBySource);
        }
    }

    private int value;

    FilterBySource(int value) {
        this.value = value;
    }

    public static FilterBySource fromValue(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("filter_by_source", getValue());
        }
    }
}
