package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Count {
    public static final TypeReference<List<Count>> TYPE_COUNT_LIST = new TypeReference<List<Count>>() {
    };

    private long id;
    private int comments;
    private int reposts;
    private int attitudes;

    // API V1 fields
    private int rt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getReposts() {
        return reposts;
    }

    public void setReposts(int reposts) {
        this.reposts = reposts;
    }

    public int getAttitudes() {
        return attitudes;
    }

    public void setAttitudes(int attitudes) {
        this.attitudes = attitudes;
    }
}
