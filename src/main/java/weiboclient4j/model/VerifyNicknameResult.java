package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyNicknameResult {
    @JsonProperty("is_legal")
    private boolean legal;

    private List<String> suggestNickname;

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public List<String> getSuggestNickname() {
        return suggestNickname;
    }

    public void setSuggestNickname(List<String> suggestNickname) {
        this.suggestNickname = suggestNickname;
    }
}
