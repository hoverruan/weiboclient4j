package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public enum CountType {
    Status("status"),
    Follower("follower"),
    Comment("cmt"),
    DirectMessage("dm"),
    MentionStatus("mention_status"),
    MentionComment("mention_cmt");

    private String value;

    CountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
