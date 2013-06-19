package weiboclient4j.params;

import weiboclient4j.StatusService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum FilterByType implements
        StatusService.GetMentionsParam,
        StatusService.GetMentionsIdsParam {

    All(0), Original(1);

    private static Map<Integer, FilterByType> map = new HashMap<Integer, FilterByType>();

    static {
        for (FilterByType filterByType : FilterByType.values()) {
            map.put(filterByType.getValue(), filterByType);
        }
    }

    private int value;

    FilterByType(int value) {
        this.value = value;
    }

    public static FilterByType fromValue(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("filter_by_type", getValue());
        }
    }
}
