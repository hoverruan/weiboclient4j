package weiboclient4j;

import org.codehaus.jackson.JsonNode;

/**
 * @author Hover Ruan
 */
public class GlobalTrend {
    private String name;
    private String query;

    public GlobalTrend() {

    }

    public GlobalTrend(JsonNode json) {
        name = json.get("name").getValueAsText();
        query = json.get("query").getValueAsText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
