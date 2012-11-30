package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum NearbySortType implements ParameterAction {
    Weight(0);

    private int value;

    NearbySortType(int value) {
        this.value = value;
    }

    public void addParameter(Parameters params) {
        params.add("sort", value);
    }
}
