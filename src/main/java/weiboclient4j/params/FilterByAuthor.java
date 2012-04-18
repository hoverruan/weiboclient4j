package weiboclient4j.params;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum FilterByAuthor {
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

    public int getValue() {
        return value;
    }

    public static FilterByAuthor fromValue(int value) {
        return map.get(value);
    }
}
