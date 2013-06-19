package weiboclient4j.params;

import weiboclient4j.StatusService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public enum Feature implements
        StatusService.GetFriendsTimelineParam,
        StatusService.GetHomeTimelineParam,
        StatusService.GetFriendsTimelineIdsParam,
        StatusService.GetUserTimelineParam,
        StatusService.GetUserTimelineIdsParam,
        StatusService.GetBilateralTimelineParam {

    All(0), Original(1), Picture(2), Video(3), Music(4);

    private static Map<Integer, Feature> map = new HashMap<Integer, Feature>();

    static {
        for (Feature feature : Feature.values()) {
            map.put(feature.getValue(), feature);
        }
    }

    private int value;

    Feature(int value) {
        this.value = value;
    }

    public static Feature fromValue(int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("feature", getValue());
        }
    }
}
