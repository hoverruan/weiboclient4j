package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SuggestionUserCategory implements ParameterAction {
    Default("default"),
    Ent("ent"),
    HkFamous("hk_famous"),
    Model("model"),
    Cooking("cooking"),
    Sports("sports"),
    Finance("finance"),
    Tech("tech"),
    Singer("singer"),
    Writer("writer"),
    Moderator("moderator"),
    Medium("medium"),
    StockPlayer("stockplayer");

    private String value;

    SuggestionUserCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void addParameter(Parameters params) {
        if (this != Default) {
            params.add("category", getValue());
        }
    }
}
