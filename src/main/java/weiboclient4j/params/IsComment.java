package weiboclient4j.params;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum IsComment implements ParameterAction {
    No(0), Current(1), Original(2), Both(3);

    private static Map<Integer, IsComment> map = new HashMap<Integer, IsComment>();

    static {
        for (IsComment isComment : IsComment.values()) {
            map.put(isComment.getValue(), isComment);
        }
    }

    private int value;

    IsComment(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static IsComment fromValue(int value) {
        return map.get(value);
    }

    public void addParameter(Parameters params) {
        if (this != No) {
            params.add("is_comment", getValue());
        }
    }
}
