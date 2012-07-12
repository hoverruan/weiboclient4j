package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchSuggestionAppResult {
    public static final TypeReference<List<SearchSuggestionAppResult>> TYPE_SEARCH_SUGGESTION_APP_RESULT_LIST =
            new TypeReference<List<SearchSuggestionAppResult>>() {
            };

    private String appsName;
    private int membersCount;

    public String getAppsName() {
        return appsName;
    }

    public void setAppsName(String appsName) {
        this.appsName = appsName;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }
}
