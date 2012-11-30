package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum PoiCategoryFlag implements ParameterAction {
    CurrentLevel(0), All(1),;

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
