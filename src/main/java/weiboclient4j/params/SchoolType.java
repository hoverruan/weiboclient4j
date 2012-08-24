package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SchoolType implements ParameterAction {
    All(0), University(1), SeniorHighSchool(2), TechnicalSecondarySchool(3), JuniorHighSchool(4), PrimarySchool(5);

    private int value;

    SchoolType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != All) {
            params.add("type", getValue());
        }
    }
}
