package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum GenderType implements
        PlaceService.GetNearbyUsersListParam {
    All(0), Male(1), Female(2);
    private int value;

    GenderType(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        params.add("gender", value);
    }
}
