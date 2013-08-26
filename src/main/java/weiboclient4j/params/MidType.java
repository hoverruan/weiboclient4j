package weiboclient4j.params;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum MidType implements ParameterAction {
    Status(1), Comment(2), Message(3);

    private static Map<Integer, MidType> map = new HashMap<Integer, MidType>();

    static {
        for (MidType midType : MidType.values()) {
            map.put(midType.getValue(), midType);
        }
    }

    private int value;

    MidType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MidType fromValue(int value) {
        return map.get(value);
    }

    public void addParameter(Parameters params) {
        params.add("type", getValue());
    }
}
