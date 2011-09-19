package weiboclient4j;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class UserList {
    private List<User> users;
    private int nextCursor;
    private int previousCursor;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(int nextCursor) {
        this.nextCursor = nextCursor;
    }

    public int getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(int previousCursor) {
        this.previousCursor = previousCursor;
    }
}
