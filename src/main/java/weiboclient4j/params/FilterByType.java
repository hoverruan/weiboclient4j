package weiboclient4j.params;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum FilterByType {
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

    public int getValue() {
        return value;
    }

    public static FilterByType fromValue(int value) {
        return map.get(value);
    }
}
