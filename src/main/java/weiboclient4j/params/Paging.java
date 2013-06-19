package weiboclient4j.params;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hover Ruan
 */
public class Paging {
    public static final String SINCE_ID = "since_id";
    public static final String PAGE = "page";
    public static final String COUNT = "count";
    public static final String MAX_ID = "max_id";
    public static final String CURSOR = "cursor";

    public static final Paging EMPTY = new Paging() {
        @Override
        public void setPage(int page) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setCount(int count) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setSinceId(long sinceId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setMaxId(long maxId) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setCursor(long cursor) {
            throw new UnsupportedOperationException();
        }
    };

    public static Paging create() {
        return new Paging();
    }

    private int page;
    private int count;
    private long sinceId;
    private long maxId;
    private long cursor;

    public Paging page(int newPage) {
        setPage(newPage);

        return this;
    }

    public Paging count(int newCount) {
        setCount(newCount);

        return this;
    }

    public Paging sinceId(long newSinceId) {
        setSinceId(newSinceId);

        return this;
    }

    public Paging maxId(long newMaxId) {
        setMaxId(newMaxId);

        return this;
    }

    public Paging cursor(long newCursor) {
        setCursor(newCursor);

        return this;
    }

    public boolean hasValue() {
        return page > 0 || count > 0 || sinceId > 0 || maxId > 0 || cursor > 0;
    }

    public Map<String, String> buildParameters() {
        Map<String, String> params = new HashMap<String, String>();

        if (page > 0) {
            params.put(PAGE, String.valueOf(page));
        }

        if (count > 0) {
            params.put(COUNT, String.valueOf(count));
        }

        if (sinceId > 0) {
            params.put(SINCE_ID, String.valueOf(sinceId));
        }

        if (maxId > 0) {
            params.put(MAX_ID, String.valueOf(maxId));
        }

        if (cursor > 0) {
            params.put(CURSOR, String.valueOf(cursor));
        }

        return params;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }
}
