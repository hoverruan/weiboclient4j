package weiboclient4j.params;

import weiboclient4j.PlaceService;

/**
 * @author Hover Ruan
 */
public enum PoiCategoryFlag implements
        PlaceService.GetPoiCategoriesParam {

    CurrentLevel(0), All(1);

    private int value;

    PoiCategoryFlag(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        if (this != CurrentLevel) {
            params.add("flag", value);
        }
    }
}
