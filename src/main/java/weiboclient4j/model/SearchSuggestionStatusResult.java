package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchSuggestionStatusResult {
    public static final TypeReference<List<SearchSuggestionStatusResult>> TYPE_SEARCH_SUGGESTION_STATUS_RESULT_LIST =
            new TypeReference<List<SearchSuggestionStatusResult>>() {
            };

    private String suggestion;
    private int count;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
