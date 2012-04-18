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
    };

    private Paging() {

    }

    public static Paging create() {
        return new Paging();
    }

    private int page;
    private int count;
    private long sinceId;
    private long maxId;

    public Paging page(int page) {
        setPage(page);

        return this;
    }

    public Paging count(int count) {
        setCount(count);

        return this;
    }

    public Paging sinceId(long sinceId) {
        setSinceId(sinceId);

        return this;
    }

    public Paging maxId(long maxId) {
        setMaxId(maxId);

        return this;
    }

    public boolean hasValue() {
        return page > 0 || count > 0 || sinceId > 0 || maxId > 0;
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

        return params;
    }

    private static void appendParameter(StringBuilder buf, String key, String value) {
        if (buf.length() > 0) {
            buf.append('&');
        }

        buf.append(key).append('=').append(value);
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
}
