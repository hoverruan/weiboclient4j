package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum UserLevel implements
        PlaceService.GetNearbyUsersListParam {
    All(0), Normal(1), VIP(2), Talent(7);

    private int value;

    UserLevel(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        params.add("level", value);
    }
}
