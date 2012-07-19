package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum SuggestionUserCategory {
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
}
