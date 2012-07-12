package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchSuggestionSchoolResult {
    public static final TypeReference<List<SearchSuggestionSchoolResult>> TYPE_SEARCH_SUGGESTION_SCHOOL_RESULT_LIST =
            new TypeReference<List<SearchSuggestionSchoolResult>>() {
            };

    private long id;
    private String schoolName;
    private int location;
    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
