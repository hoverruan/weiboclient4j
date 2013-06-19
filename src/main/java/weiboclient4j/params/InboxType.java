package weiboclient4j.params;

import weiboclient4j.StatusService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum InboxType implements
        StatusService.QueryIdParam,
        StatusService.QueryIdListParam {

    Outbox(0), Inbox(1);

    private static Map<Integer, InboxType> map = new HashMap<Integer, InboxType>();

    static {
        for (InboxType inboxType : InboxType.values()) {
            map.put(inboxType.getValue(), inboxType);
        }
    }

    private int value;

    InboxType(int value) {
        this.value = value;
    }

    public static InboxType fromValue(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != Outbox) {
            params.add("inbox", getValue());
        }
    }
}
