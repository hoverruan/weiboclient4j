package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Friendship {
    private FriendshipInfo target;
    private FriendshipInfo source;

    public FriendshipInfo getTarget() {
        return target;
    }

    public void setTarget(FriendshipInfo target) {
        this.target = target;
    }

    public FriendshipInfo getSource() {
        return source;
    }

    public void setSource(FriendshipInfo source) {
        this.source = source;
    }
}
