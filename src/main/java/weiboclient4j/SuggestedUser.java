package weiboclient4j;

/**
 * @author Hover Ruan
 */
public class SuggestedUser {
    private String reason;
    private User user;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
