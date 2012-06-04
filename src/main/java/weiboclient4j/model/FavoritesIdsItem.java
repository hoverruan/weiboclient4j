package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoritesIdsItem {
    private long status;
    private List<Tag> tags;
    private Date favoritedTime;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Date getFavoritedTime() {
        return favoritedTime;
    }

    public void setFavoritedTime(Date favoritedTime) {
        this.favoritedTime = favoritedTime;
    }
}
